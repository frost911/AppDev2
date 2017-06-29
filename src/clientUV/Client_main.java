package clientUV;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import serverUV.UV;

/**
 *
 * @author TKulhavy
 */
public class Client_main {

    public static void main(String[] args) {
        try {
            UV uv = (UV) Naming.lookup("rmi://localhost:1099/urlaubsvertretung");
            String input = "";
            
            // Objekte erstellen
            
            uv.abteilungAnlegen("Anwendungsentwicklung");
            
            uv.mitarbeiterAnlegen("Thomas Seitz","Anwendungsentwicklung", 30);
            uv.mitarbeiterAnlegen("Maurice Wisskirchen","Anwendungsentwicklung", 30);
            uv.mitarbeiterAnlegen("Patrick Lemm","Anwendungsentwicklung", 30);
            uv.mitarbeiterAnlegen("Tim Kulhavy","Anwendungsentwicklung", 30);
            
            uv.setAbteilungsleiter(uv.abteilungLesen("Anwendungsentwicklung").getID(),uv.mitarbeiterLesen("Tim Kulhavy").getID());
            
            

            //todo..... 
           // uv.urlaubsantragEntscheiden(1, true);

        } catch (RemoteException ex) {
            System.out.println("RMI Fehler: " + ex.getMessage());
        } catch (MalformedURLException ex) {
            System.out.println("Fehler in der RMI URL: " + ex.getMessage());
        } catch (NotBoundException ex) {
            System.out.println("Fehler beim verbinden mit Port 1099: " + ex.getMessage());
        }
    }

}
