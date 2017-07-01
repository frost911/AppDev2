package serverUV;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 *
 * @author TKulhavy
 */
public class Server_main {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            UVImpl urlaubsvertretung = new UVImpl();
            LocateRegistry.createRegistry(1099);
            Naming.bind("rmi://localhost:1099/urlaubsvertretung", urlaubsvertretung);
        } catch (RemoteException ex) {
            System.out.println("RMI Fehler: " + ex.getMessage());
        } catch (MalformedURLException ex) {
            System.out.println("Fehler in der RMI URL: " + ex.getMessage());
        } catch (AlreadyBoundException ex) {
            System.out.println("Fehler beim verbinden mit Port 1099: " + ex.getMessage());
        }
    }
}
