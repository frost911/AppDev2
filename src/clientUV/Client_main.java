package clientUV;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.Date;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import serverUV.UV;

public class Client_main {

    // Variablen für Farbige Textausgabe
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static void main(String[] args) throws IOException {
        try {
            UV uv = (UV) Naming.lookup("rmi://localhost:1099/urlaubsvertretung");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String input = "";

            // Ausgabe "Header"
            System.out.println(ANSI_GREEN + "AppDev2 SS 2017" + ANSI_GREEN);
            System.out.println(ANSI_GREEN + "Dozent: " + ANSI_GREEN + ANSI_RED + "Herr Barth");
            System.out.println(ANSI_GREEN + "Gruppe: " + ANSI_GREEN + ANSI_RED + " Kulhavy, Lemm, Wißkirchen, Seitz" + ANSI_RED);
            System.out.println(ANSI_GREEN + "Programm:  " + ANSI_GREEN + ANSI_RED + "Urlaubsvertretung" + ANSI_RED);
            System.out.println();
            System.out.println(ANSI_RED + "<-Beispielhafter Programmablauf->" + ANSI_RED);
            System.out.println();

            // Objekte in Datenbank schreiben
            System.out.println(ANSI_GREEN + uv.abteilungAnlegen("Anwendungsentwicklung"));
            System.out.println(ANSI_GREEN + uv.mitarbeiterAnlegen("Thomas Seitz", uv.abteilungLesen("Anwendungsentwicklung"), 30));
            System.out.println(ANSI_GREEN + uv.mitarbeiterAnlegen("Maurice Wisskirchen", uv.abteilungLesen("Anwendungsentwicklung"), 30));
            System.out.println(ANSI_GREEN + uv.mitarbeiterAnlegen("Patrick Lemm", uv.abteilungLesen("Anwendungsentwicklung"), 30));
            System.out.println(ANSI_GREEN + uv.mitarbeiterAnlegen("Tim Kulhavy", uv.abteilungLesen("Anwendungsentwicklung"), 30));
            System.out.println(ANSI_GREEN + uv.setAbteilungsleiter(uv.abteilungLesen("Anwendungsentwicklung").getID(), uv.mitarbeiterLesen("Tim Kulhavy").getID()));
            System.out.println(ANSI_GREEN + uv.abteilungLesen("Anwendungsentwicklung"));

            //Programmablauf
            System.out.println(ANSI_GREEN + "Mitarbeiter Seitz hat " + uv.urlaubstageLesen(uv.mitarbeiterLesen("Thomas Seitz").getID()) + " Urlaubstage");
            System.out.println(ANSI_GREEN + uv.urlaubsantragStellen(uv.mitarbeiterLesen("Thomas Seitz"), null, new Date(117, 7, 2), new Date(117, 7, 10)));

            //Entscheidung 1
            System.out.print(ANSI_YELLOW + "Ihre Eingabe:");
            input = br.readLine();
            ArrayList<Integer> UAs = uv.readAllUAsForMA(uv.mitarbeiterLesen("Thomas Seitz").getID());
            if (input.equalsIgnoreCase("J")) {
                System.out.println(ANSI_GREEN + uv.urlaubsantragEntscheiden(UAs.get(0), true)); // get(0) gibt immer den zuletzt eingereichten Urlaubsantrag für den Mitarbeiter
            } else {
                System.out.println(ANSI_GREEN + uv.urlaubsantragEntscheiden(UAs.get(0), false)); // get(0) gibt immer den zuletzt eingereichten Urlaubsantrag für den Mitarbeiter
            }
            System.out.println(ANSI_GREEN + "Mitarbeiter Seitz hat noch " + uv.mitarbeiterLesen("Thomas Seitz").getUrlaubstage() + " Urlaubstage");
            System.out.println(ANSI_RED + "Mitarbeiter Lemm will ebenfalls Urlaub nehmen.");
            System.out.println(ANSI_GREEN + "Mitarbeiter Lemm hat " + uv.urlaubstageLesen(uv.mitarbeiterLesen("Patrick Lemm").getID()) + " Urlaubstage");
            System.out.println(ANSI_GREEN + uv.urlaubsantragStellen(uv.mitarbeiterLesen("Patrick Lemm"), null, new Date(117, 7, 2), new Date(117, 7, 10)));

            //Entscheidung 2
            System.out.print(ANSI_YELLOW + "Ihre Eingabe:");
            input = br.readLine();
            UAs = uv.readAllUAsForMA(uv.mitarbeiterLesen("Patrick Lemm").getID());
            if (input.equalsIgnoreCase("J")) {
                System.out.println(ANSI_GREEN + uv.urlaubsantragEntscheiden(UAs.get(0), true)); // get(0) gibt immer den zuletzt eingereichten Urlaubsantrag für den Mitarbeiter
            } else {
                System.out.println(ANSI_GREEN + uv.urlaubsantragEntscheiden(UAs.get(0), false)); // get(0) gibt immer den zuletzt eingereichten Urlaubsantrag für den Mitarbeiter
            }
            System.out.println(ANSI_GREEN + "Mitarbeiter Lemm hat " + uv.urlaubstageLesen(uv.mitarbeiterLesen("Patrick Lemm").getID()) + " Urlaubstage");
            System.out.println(ANSI_RED + "Da der Urlaub abgelehnt wurde, stellt Mitarbeiter Lemm einen erneuten Antrag.");
            System.out.println(ANSI_GREEN + uv.urlaubsantragStellen(uv.mitarbeiterLesen("Patrick Lemm"), uv.mitarbeiterLesen("Maurice Wisskirchen"), new Date(117, 7, 2), new Date(117, 7, 10)));
            System.out.println(ANSI_GREEN + "Mitarbeiter Lemm hat " + uv.urlaubstageLesen(uv.mitarbeiterLesen("Patrick Lemm").getID()) + " Urlaubstage");

            //Programmablauf Ende
            System.out.println();
            System.out.println(ANSI_RED + "<-Ende Beispielhafter Programmablauf->" + ANSI_RED);

            //close Buffered Reader
            br.close();

            //exception handling
        } catch (RemoteException ex) {
            System.out.println("RMI Fehler: " + ex.getMessage());
        } catch (MalformedURLException ex) {
            System.out.println("Fehler in der RMI URL: " + ex.getMessage());
        } catch (NotBoundException ex) {
            System.out.println("Fehler beim verbinden mit Port 1099: " + ex.getMessage());
        }
    }
}
