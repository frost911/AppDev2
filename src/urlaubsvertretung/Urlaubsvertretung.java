/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package urlaubsvertretung;

import java.util.Date;

/**
 *
 * @author TKulhavy
 */
public class Urlaubsvertretung {

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
    }

}
