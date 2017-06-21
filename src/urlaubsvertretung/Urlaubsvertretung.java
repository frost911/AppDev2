/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package urlaubsvertretung;

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
        System.out.println(a.getID() + " " + a2.getID());
    }

}
