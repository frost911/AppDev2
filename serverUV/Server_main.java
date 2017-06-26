/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverUV;

import java.util.Date;
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.sql.SQLException;

/**
 *
 * @author TKulhavy
 */
public class Server_main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Mitarbeiter leiter = new Mitarbeiter("Peter", 10);
        Abteilung a = new Abteilung(leiter, "Neue Abteilung");
        Abteilung a2 = new Abteilung("zweite Abteilung");
        Mitarbeiter ma = new Mitarbeiter("Tom", a2, 10);
        Urlaubsantrag u = new Urlaubsantrag(ma, leiter, new Date(2017, 6, 1), new Date(2017, 6, 8));
        System.out.println(ma.getUrlaubstage());

        try {
            UV UV = new UV();
            //Der Server wird gestartet und unter benutzerverwaltung "veröffentlicht"
            LocateRegistry.createRegistry(1099);
            Naming.bind("rmi://localhost:1099/benutzerverwaltung", UV);
            // nun wartet der server auf anfragen vom client...
        } catch (RemoteException ex) {
            System.out.println("Ein remote fehler ist aufgetreten: " + ex.getMessage());
        } catch (AlreadyBoundException ex) {
            System.out.println("Fehler: " + ex.getMessage());
        } catch (MalformedURLException ex) {
            System.out.println("Fehler in der rmi Adresse: " + ex.getMessage());
        }
    }
}
