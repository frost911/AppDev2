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
public class Mitarbeiter {

    private String Name;
    private static int IDCOUNTER = 0;
    private final int ID;
    private Abteilung abteilung;

    public Abteilung getAbteilung() {
        return abteilung;
    }

    public void setAbteilung(Abteilung abteilung) {
        this.abteilung = abteilung;
    }

    public int getID() {
        return ID;
    }

    private int urlaubstage;

    public int getUrlaubstage() {
        return urlaubstage;
    }

    public void setUrlaubstage(int urlaubstage) {
        this.urlaubstage = urlaubstage;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public Mitarbeiter(String Name, Abteilung abteilung, int urlaubstage) {
        this.Name = Name;
        this.ID = Mitarbeiter.IDCOUNTER;
        Mitarbeiter.IDCOUNTER++;
        this.abteilung = abteilung;
        this.urlaubstage = urlaubstage;
    }

    public Mitarbeiter(String Name, int urlaubstage) {
        this.Name = Name;
        this.ID = Mitarbeiter.IDCOUNTER;
        Mitarbeiter.IDCOUNTER++;
        this.urlaubstage = urlaubstage;
    }

}
