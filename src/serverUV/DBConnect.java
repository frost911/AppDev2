package serverUV;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author TKulhavy
 */
public class DBConnect {

    private String url = "jdbc:mysql://localhost:3306/test";
    private String username = "root";
    private String password = "test1234";
    private Connection connection;

    public DBConnect() throws SQLException {
        System.out.println("Verbinde zur Datenbank...");
        try {
            // Eine verbindung wird versucht herzustellen
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Verbindung erfolgreich!");
        } catch (SQLException ex) {
            throw new SQLException("Kann nicht zur Datenbank verbinden! " + ex.getMessage());
        }
    }

    public void close() {
        try {
            // verbindung kann ggf. geschlossen werden
            connection.close();
        } catch (SQLException ex) {
            System.out.println("Datenbankverbindung kann nicht geschlossen werden! " + ex.getMessage());
        }
    }

    /**
     * Methode um alle Benutzer aus der Datenbank zu laden und als ArrayList
     * voller Benutzer Objekte zurückzugeben
     *
     * @return ArrayList<Benutzer> benutzerliste
     */
    public ArrayList<Benutzer> ladeBenutzer() {
        System.out.println("Lade Benutzer:");
        System.out.println("---------------------------------------");
        ArrayList<Benutzer> benutzerliste = new ArrayList<>();
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM benutzer");
            while (rs.next()) {
                Benutzer benutzer = new Benutzer(rs.getString("name"));
                benutzerliste.add(benutzer);
                System.out.println(benutzer.name);
            }
            st.close();
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Fehler beim laden der Benutzer! " + ex.getMessage());
        }
        System.out.println("---------------------------------------");
        return benutzerliste;
    }

    /**
     * Methode um einen Benutzer zu speichern.
     *
     * @param benutzer Ein benutzer wird übergeben
     */
    public void save(Urlaubsantrag antrag) {
        try {
            PreparedStatement st = connection.prepareStatement("INSERT INTO `benutzer` (`id`, `name`) VALUES (NULL, ?)");
            st.setString(1, antrag.name);
            st.executeUpdate();
            st.close();
        } catch (SQLException ex) {
            System.out.println("Fehler beim speichern des Benutzers (" + benutzer.name + ")! " + ex.getMessage());
        }
    }

    /**
     * Methode um alle Benutzer zu löschen, sodass die Tabelle leer ist
     */
    public void alleBenutzerLoeschen() {
        try {
            Statement st = connection.createStatement();
            st.executeUpdate("DELETE FROM `benutzer`");
            st.close();
        } catch (SQLException ex) {
            System.out.println("Fehler beim löschen aller Benutzer! " + ex.getMessage());
        }
    }
}
