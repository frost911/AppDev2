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

    public Abteilung(Mitarbeiter abteilungsleiter, String Name, int ID) {
        this.ID = ID;
        this.abteilungsleiter = abteilungsleiter;
        this.Name = Name;
    }

    public Abteilung(String Name, int ID) {
        this.ID = ID;
        this.Name = Name;
    }
}
