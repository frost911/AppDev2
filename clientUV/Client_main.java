package clientUV;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import serverUV.UVInterface;

/**
 *
 * @author TKulhavy
 */
public class Client_main {

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            //connect
            UVInterface uv = (UVInterface) Naming.lookup("rmi://localhost:1099/benutzerverwaltung");
            String input = "";

            while (true) {
                System.out.println("Benutzername oder 'exit' oder 'liste' oder 'clear' eingeben:");
                input = scanner.next();
                if (input.equals("exit")) {
                    break;
                } else if (input.equals("liste")) {
                    System.out.println("Alle Benutzer die angelegt wurden:");
                    System.out.println("------------------------------------");
                    System.out.println(uv.getAllUA());
                } else {
                    uv.urlaubsantragStellen(); // wie? Import für MA macht keinen sinn
                }
            }
        } catch (NotBoundException ex) {
            System.out.println("Fehler: " + ex.getMessage());
        } catch (MalformedURLException ex) {
            System.out.println("Fehler in der rmi Adresse: " + ex.getMessage());
        } catch (RemoteException ex) {
            System.out.println("Ein remote fehler ist aufgetreten: " + ex.getMessage());
        }
    }

}
