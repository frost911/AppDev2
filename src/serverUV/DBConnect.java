package serverUV;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author TKulhavy
 */
public class DBConnect {

    private String url = "jdbc:mysql://localhost:3306/urlaubsvertretung";
    private String user = "root";
    private String pw = "";
    private Connection conn;

    public void saveUA(Urlaubsantrag antrag) {
        try {
            Mitarbeiter v = antrag.getVertreter();
            int v_id = 0;
            if (v != null) {
                v_id = v.getID();
            }
            PreparedStatement ps = conn.prepareStatement("INSERT INTO `urlaubsantrag` (`id`, `mitarbeiter_id`,`vertreter_id`, `urlaubsbeginn`, `urlaubsende`, `genehmigung_chef`) VALUES (?, ?, ?, ?, ?, ?)");
            ps.setInt(1, antrag.getID());
            ps.setInt(2, antrag.getMA().getID());
            ps.setInt(3, v_id);
            ps.setDate(4, antrag.getUrlaubsbeginn());
            ps.setDate(5, antrag.getUrlaubsende());
            ps.setBoolean(6, antrag.isGenehmigt());
            ps.execute();
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Fehler beim anlegen eines Urlaubsantrags: (ID " + antrag.toString() + "): " + ex.getMessage());
        }
    }

    public void updateUA(int ID, boolean genehmigt) {
        try {
            PreparedStatement ps = conn.prepareStatement("UPDATE `urlaubsantrag` SET `genehmigung_chef` = ? WHERE `urlaubsantrag`.`id` = ?");
            ps.setBoolean(1, genehmigt);
            ps.setInt(2, ID);
            ps.execute();
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Fehler beim entscheiden über einen Urlaubsantrag: (ID " + ID + "): " + ex.getMessage());
        }
    }

    public void updateMA(int ID, int urlaubstage) {
        try {
            PreparedStatement ps = conn.prepareStatement("UPDATE `mitarbeiter` SET `Urlaubstage` = ? WHERE `mitarbeiter`.`id` = ?");
            ps.setInt(1, urlaubstage);
            ps.setInt(2, ID);
            ps.execute();
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Fehler beim aktualisieren eines Mitarbeiters: (ID " + ID + "): " + ex.getMessage());
        }
    }

    public Mitarbeiter readMA(int ID) {
        Mitarbeiter ma = null;
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM `mitarbeiter` WHERE `id` = ?");
            ps.setInt(1, ID);
            ps.execute();
            ResultSet res = ps.getResultSet();
            if (res.next()) {
                ma = new Mitarbeiter(res.getString("Name"), this.readDpt(res.getInt("abteilungs_id")), res.getInt("Urlaubstage"), res.getInt("id"));
            }
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Fehler beim lesen eines Mitarbeiters: (ID " + ID + "): " + ex.getMessage());
        }
        return ma;
    }

    public Urlaubsantrag readUA(int ID) {
        Urlaubsantrag ua = null;
        Mitarbeiter v = null;
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM `urlaubsantrag` WHERE `id` = ?");
            ps.setInt(1, ID);
            ps.execute();
            ResultSet res = ps.getResultSet();
            if (res.next()) {
                if (res.getInt("vertreter_id") != 0) {
                    v = this.readMA(res.getInt("vertreter_id"));
                }
            }
            ua = new Urlaubsantrag(this.readMA(res.getInt("mitarbeiter_id")), v, res.getDate("urlaubsbeginn"), res.getDate("urlaubsende"), res.getInt("ID"));
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Fehler beim lesen eines Urlaubsantrags: (ID " + ID + "): " + ex.getMessage());
        }
        return ua;
    }

    public Abteilung readDpt(int ID) {
        Abteilung dpt = null;
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM `abteilung` WHERE `id` = ?");
            ps.setInt(1, ID);
            ps.execute();
            ResultSet res = ps.getResultSet();
            if (res.next()) {
                dpt = new Abteilung(res.getString("name"), res.getInt("id"));
            }
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Fehler beim lesen einer Abteilung: (ID " + ID + "): " + ex.getMessage());
        }
        return dpt;
    }

    public int readUrlaubstage(int ID) {
        int urlaubstage = 0;
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT `Urlaubstage` FROM `mitarbeiter` WHERE `id` = ?");
            ps.setInt(1, ID);
            ps.execute();
            ResultSet res = ps.getResultSet();
            while (res.next()) {
                urlaubstage = res.getInt("Urlaubstage");
            }
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Fehler beim lesen eines Mitarbeiters: (ID " + ID + "): " + ex.getMessage());
        }
        return urlaubstage;
    }

    public void saveMA(Mitarbeiter ma) {
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO `mitarbeiter` (`id`,`abteilungs_id`,`name`,`urlaubstage`) VALUES (NULL, ?, ?, ?)");
            ps.setInt(1, ma.getAbteilung().getID());
            ps.setString(2, ma.getName());
            ps.setInt(3, ma.getUrlaubstage());
            ps.execute();
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Fehler beim anlegen eines Mitarbeiters: (ID " + ma.toString() + "): " + ex.getMessage());
        }
    }

    public void close() {
        try {
            conn.close();
        } catch (SQLException ex) {
            System.out.println("Fehler beim schließen der Verbindung: " + ex.getMessage());
        }
    }

    public DBConnect() {
        try {
            conn = DriverManager.getConnection(url, user, pw);
            System.out.println("Verbindung erfolgreich hergestellt.");
        } catch (SQLException ex) {
            System.out.println("Fehler beim herstellen der Verbindung: " + ex.getMessage());
        }
    }
}
