package clientUV;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import serverUV.UV;
import serverUV.Mitarbeiter;
import serverUV.Abteilung;

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
            Abteilung Anwendungsentwicklung = new Abteilung("Anwendungsentwicklung", 120);
            Mitarbeiter thomas = new Mitarbeiter("Thomas Seitz",Anwendungsentwicklung, 30, 66 );
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
