package serverUV;

import java.io.Serializable;

public class Mitarbeiter implements Serializable {

    private String Name;
    private final int ID;
    private Abteilung abteilung;
    private int urlaubstage;

    /**
     * @return Abteilung
     */
    public Abteilung getAbteilung() {
        return abteilung;
    }

    /**
     * @param abteilung
     */
    public void setAbteilung(Abteilung abteilung) {
        this.abteilung = abteilung;
    }

    /**
     * @return ID
     */
    public int getID() {
        return ID;
    }

    /**
     * @return
     */
    public int getUrlaubstage() {
        return urlaubstage;
    }

    /**
     * @param urlaubstage
     */
    public void setUrlaubstage(int urlaubstage) {
        this.urlaubstage = urlaubstage;
    }

    /**
     * @return Name
     */
    public String getName() {
        return Name;
    }

    /**
     * @param Name
     */
    public void setName(String Name) {
        this.Name = Name;
    }

    /**
     * @return Mitarbeiter to String
     */
    @Override
    public String toString() {
        return "Mitarbeiter { " + "Name = " + Name + ", ID = " + ID + ", Urlaubstage = " + urlaubstage + '}';
    }

    /**
     * Constructor
     *
     * @param Name
     * @param abteilung
     * @param urlaubstage
     * @param ID
     */
    public Mitarbeiter(String Name, Abteilung abteilung, int urlaubstage, int ID) {
        this.Name = Name;
        this.abteilung = abteilung;
        this.urlaubstage = urlaubstage;
        this.ID = ID;
    }
}
