package serverUV;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Diese Klasse ist die Implementierung des Interfaces
 * BenutzerverwaltungInterface. Sie muss das Interface impelemntieren und mit
 * UnicastRemoteObject erweitert werden. Hier wird die ganze Logik des Programms
 * abgebildet. Es können zum Beispiel Benutzer erstellt werden, alle ausgelesen
 * oder alle gelöscht werden.
 *
 * @author Jan
 */
public class UV extends UnicastRemoteObject implements UVInterface {

    DBConnect db;

    @Override
    public void urlaubsantragStellen(Mitarbeiter MA, Mitarbeiter vertreter, Date urlaubsbeginn, Date urlaubsende) throws RemoteException {
        Urlaubsantrag antrag = new Urlaubsantrag(MA, vertreter, urlaubsbeginn, urlaubsende);
        db.save(antrag);
        System.out.println("Antrag " + antrag.toString() + " wurde gestellt.");
    }

    public Benutzerverwaltung() throws RemoteException, SQLException {
        this.benutzer = new ArrayList<>();
        this.db = new Datenbank(); // die Datenbank wird initiiert
        benutzer.addAll(db.ladeBenutzer()); // alle Benutzer der Datenbank werden geladen und der lokalen
        // Liste im Arbeitsspeicher (also der ArrayList benutzer) hinzugefügt
    }

    @Override
    public String alleBenutzer() throws RemoteException {
        String allebenutzer = "";
        // alle Benutzer werden aus der lokalen liste ausgelesen und in einen String geschrieben und zurück gegeben
        for (Benutzer b : benutzer) {
            allebenutzer += b.name + "\n";
        }
        return allebenutzer;
    }

    @Override
    public void alleBenutzerLoeschen() throws RemoteException {
        db.alleBenutzerLoeschen(); // alle benutzer werden in der Datenbank
        benutzer.clear(); // und lokal gelöscht
        System.out.println("Alle Benutzer wurden gelöscht");
    }

}
