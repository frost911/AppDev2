package serverUV;

import java.io.Serializable;

public class Abteilung implements Serializable {

    private final int ID;
    private Mitarbeiter abteilungsleiter;
    private String Name;

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

    @Override
    public String toString() {
        return "Abteilung {" + " ID= " + ID + ", abteilungsleiter = " + abteilungsleiter + ", Name = " + Name + '}';
    }

    public Abteilung(Mitarbeiter abteilungsleiter, String Name, int ID) {
        this.ID = ID;
        this.abteilungsleiter = abteilungsleiter;
        this.Name = Name;
    }

    public Abteilung(String Name, int id) {
        this.Name = Name;
        this.ID = id;
    }

    public Abteilung(String Name) {
        this.Name = Name;
        this.ID = 0;
    }
}
