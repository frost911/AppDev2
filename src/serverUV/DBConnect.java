package serverUV;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
            System.out.println("Fehler beim anlegen eines Urlaubsantrags: (" + antrag.toString() + "): " + ex.getMessage());
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

    public Mitarbeiter readMA(String name) {
        Mitarbeiter ma = null;
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM `mitarbeiter` WHERE `name` = ?");
            ps.setString(1, name);
            ps.execute();
            ResultSet res = ps.getResultSet();
            if (res.next()) {
                ma = new Mitarbeiter(res.getString("Name"), this.readDpt(res.getInt("abteilungs_id")), res.getInt("Urlaubstage"), res.getInt("id"));
            }
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Fehler beim lesen des Mitarbeiters: (Name " + name + "): " + ex.getMessage());
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

    public ArrayList<Urlaubsantrag> readAllUAsForMA(int MA_ID) {
        Mitarbeiter v = null;
        ArrayList<Urlaubsantrag> UAs = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM `urlaubsantrag` WHERE `mitarbeiter_id` = ? ORDER BY `id` DESC");
            ps.setInt(1, MA_ID);
            ps.execute();
            ResultSet res = ps.getResultSet();
            while (res.next()) {
                if (res.getInt("vertreter_id") != 0) {
                    v = this.readMA(res.getInt("vertreter_id"));
                }
                Urlaubsantrag ua = new Urlaubsantrag(this.readMA(MA_ID), v, res.getDate("urlaubsbeginn"), res.getDate("urlaubsende"), res.getInt("ID"));
                UAs.add(ua);
            }
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Fehler beim lesen der Urlaubsanträge für Mitarbeiter: (MA_ID " + MA_ID + "): " + ex.getMessage());
        }
        return UAs;
    }

    public Abteilung readDpt(String abteilungsname) {
        Abteilung dpt = null;
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM `abteilung` WHERE `name` = ?");
            ps.setString(1, abteilungsname);
            ps.execute();
            ResultSet res = ps.getResultSet();
            if (res.next()) {
                if (res.getInt("abteilungsleiter") == 0){
                    dpt = new Abteilung(res.getString("name"), res.getInt("ID"));   
                }
                else{
                    Mitarbeiter ma = this.readMA(res.getInt("abteilungsleiter"));
                    dpt = new Abteilung(ma, res.getString("name"), res.getInt("ID"));              
                }
            }
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Fehler beim lesen einer der Abteilung: (Name " + abteilungsname + " ):" + ex.getMessage());
        }
        return dpt;
    }

    public Abteilung readDpt(int ID) {
        Abteilung dpt = null;
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM `abteilung` WHERE `id` = ?");
            ps.setInt(1, ID);
            ps.execute();
            ResultSet res = ps.getResultSet();
            if (res.next()) {               
                    dpt = new Abteilung(res.getString("name"), res.getInt("ID"));   
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

    public void setAL(int AB_ID, int MA_ID) {
        try {
            PreparedStatement ps = conn.prepareStatement(("UPDATE `abteilung` SET `abteilungsleiter` = ? WHERE `abteilung`.`id` = ?"));
            ps.setInt(1, MA_ID);
            ps.setInt(2, AB_ID);
            ps.execute();
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Fehler beim setzen des Abteilungsleiters: (Abteilungsleiter ID " + MA_ID + ", Abteilungs ID " + AB_ID + "): " + ex.getMessage());
        }
    }

    public void saveDpt(Abteilung ab) {
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO `abteilung` (`id`,`name`,`abteilungsleiter`) VALUES (NULL, ?, ?)");
            ps.setString(1, ab.getName());
            if (ab.getAbteilungsleiter() != null && !ab.getAbteilungsleiter().getName().isEmpty()) {
                ps.setInt(2, ab.getAbteilungsleiter().getID());
            } else {
                ps.setInt(2, 0);
            }
            ps.execute();
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Fehler beim anlegen einer Abteilung: (Abteilung " + ab.toString() + "): " + ex.getMessage());
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
