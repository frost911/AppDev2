package clientUV;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;
import serverUV.UV;

/**
 *
 * @author TKulhavy
 */
public class Client_main {

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            //connect
            UV uv = (UV) Naming.lookup("rmi://localhost:1099/urlaubsvertretung");
            String input = "";
            //Objekte anlegen
            uv.urlaubsantragEntscheiden(1, true);
        } catch (NotBoundException ex) {
            System.out.println("Fehler: " + ex.getMessage());
        } catch (MalformedURLException ex) {
            System.out.println("Fehler in der rmi Adresse: " + ex.getMessage());
        } catch (RemoteException ex) {
            System.out.println("Ein remote fehler ist aufgetreten: " + ex.getMessage());
        }
    }

}
