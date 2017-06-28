package clientUV;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
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

            uv.urlaubsantragEntscheiden(1, true);

        } catch (RemoteException ex) {
            System.out.println("RMI Fehler: " + ex.getMessage());
        } catch (MalformedURLException ex) {
            System.out.println("Fehler in der RMI URL: " + ex.getMessage());
        } catch (NotBoundException ex) {
            System.out.println("Fehler beim verbinden mit Port 1099: " + ex.getMessage());
        }
    }

}
