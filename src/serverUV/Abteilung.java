package serverUV;

import java.io.Serializable;

public class Abteilung implements Serializable {
    
    private final int ID;
    private Mitarbeiter abteilungsleiter;
    private String Name;
    
    /**
     * @return Name
     */
    public String getName() {
        return Name;
    }
    
    /**
     * @param Name
     * sets Name
     */
    public void setName(String Name) {
        this.Name = Name;
    }
    
    /**
     * @return ID
     */
    public int getID() {
        return ID;
    }
    
    /**
     * @return Abteilungsleiter
     */
    public Mitarbeiter getAbteilungsleiter() {
        return abteilungsleiter;
    }
    
    /**
     * @param abteilungsleiter 
     * sets Abteilungsleiter
     */
    public void setAbteilungsleiter(Mitarbeiter abteilungsleiter) {
        this.abteilungsleiter = abteilungsleiter;
    }
    
    /**
     * @return Abteilung to String
     */
    @Override
    public String toString() {
        return "Abteilung {" + " ID = " + ID + ", Abteilungsleiter = " + abteilungsleiter + ", Name = " + Name + '}';
    }
    
    /**
     * @param abteilungsleiter
     * @param Name
     * @param ID 
     */
    public Abteilung(Mitarbeiter abteilungsleiter, String Name, int ID) {
        this.ID = ID;
        this.abteilungsleiter = abteilungsleiter;
        this.Name = Name;
    }
    
    /**
     * Constructor 1
     * @param Name
     * @param id 
     */
    public Abteilung(String Name, int id) {
        this.Name = Name;
        this.ID = id;
    }
    
    /**
     * Constructor 2
     * @param Name 
     */
    public Abteilung(String Name) {
        this.Name = Name;
        this.ID = 0;
    }
}
