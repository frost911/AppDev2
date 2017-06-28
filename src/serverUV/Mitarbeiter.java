package serverUV;

import java.io.Serializable;

/**
 *
 * @author TKulhavy
 */
public class Mitarbeiter implements Serializable {

    private String Name;
    private final int ID;
    private Abteilung abteilung;
    private int urlaubstage;

    public Abteilung getAbteilung() {
        return abteilung;
    }

    public void setAbteilung(Abteilung abteilung) {
        this.abteilung = abteilung;
    }

    public int getID() {
        return ID;
    }

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

    @Override
    public String toString() {
        return "Mitarbeiter { " + "Name = " + Name + ", ID = " + ID + ", urlaubstage = " + urlaubstage + '}';
    }

    public Mitarbeiter(String Name, Abteilung abteilung, int urlaubstage, int ID) {
        this.Name = Name;
        this.abteilung = abteilung;
        this.urlaubstage = urlaubstage;
        this.ID = ID;
    }
}
