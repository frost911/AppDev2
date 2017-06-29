package clientUV;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.Date;
import java.util.ArrayList;
import serverUV.UV;
import serverUV.Urlaubsantrag;

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
            /*uv.abteilungAnlegen("Anwendungsentwicklung");
            uv.mitarbeiterAnlegen("Thomas Seitz", uv.abteilungLesen("Anwendungsentwicklung"), 30);
            uv.mitarbeiterAnlegen("Maurice Wisskirchen", uv.abteilungLesen("Anwendungsentwicklung"), 30);
            uv.mitarbeiterAnlegen("Patrick Lemm", uv.abteilungLesen("Anwendungsentwicklung"), 30);
            uv.mitarbeiterAnlegen("Tim Kulhavy", uv.abteilungLesen("Anwendungsentwicklung"), 30);

            uv.setAbteilungsleiter(uv.abteilungLesen("Anwendungsentwicklung").getID(), uv.mitarbeiterLesen("Tim Kulhavy").getID());*/
            uv.urlaubsantragStellen(uv.mitarbeiterLesen("Thomas Seitz"), null, new Date(117, 7, 2), new Date(117, 7, 10));
            System.out.println(uv.urlaubstageLesen(uv.mitarbeiterLesen("Thomas Seitz").getID()));
            ArrayList<Urlaubsantrag> UAs = uv.readAllUAsForMA(uv.mitarbeiterLesen("Thomas Seitz").getID());
            uv.urlaubsantragEntscheiden(UAs.get(0).getID(), true); // get(0) gibt immer den zuletzt eingereichten Urlaubsantrag für den Mitarbeiter
            System.out.println(uv.urlaubstageLesen(uv.mitarbeiterLesen("Thomas Seitz").getID()));
        } catch (RemoteException ex) {
            System.out.println("RMI Fehler: " + ex.getMessage());
        } catch (MalformedURLException ex) {
            System.out.println("Fehler in der RMI URL: " + ex.getMessage());
        } catch (NotBoundException ex) {
            System.out.println("Fehler beim verbinden mit Port 1099: " + ex.getMessage());
        }
    }

}
