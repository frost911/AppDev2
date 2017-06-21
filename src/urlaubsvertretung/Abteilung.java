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
public class Abteilung {

    private final int ID;
    private Mitarbeiter abteilungsleiter;
    private String Name;
    private static int IDCOUNTER = 0;

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public int getID() {
        return ID;
    }

    public Mitarbeiter getAbteilungsleiter() {
        return abteilungsleiter;
    }

    public void setAbteilungsleiter(Mitarbeiter abteilungsleiter) {
        this.abteilungsleiter = abteilungsleiter;
    }

    public Abteilung(Mitarbeiter abteilungsleiter, String Name) {
        this.ID = Abteilung.IDCOUNTER;
        Abteilung.IDCOUNTER++;
        this.abteilungsleiter = abteilungsleiter;
        this.Name = Name;
    }

    public Abteilung(String Name) {
        this.ID = Abteilung.IDCOUNTER;
        Abteilung.IDCOUNTER++;
        this.Name = Name;
    }
}
