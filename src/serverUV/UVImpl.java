package serverUV;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Date;
import java.util.ArrayList;

/**
 * Creates DB Object 
 * Implements Interface UV
 * extends UnicastRemoteObject
 */
public class UVImpl extends UnicastRemoteObject implements UV {

    DBConnect db;
    
    // Mitarbeiter
    
    /**
     * @param Name
     * @param abteilung
     * @param urlaubstage
     * @return string output
     * @throws RemoteException 
     */
    @Override
    public String mitarbeiterAnlegen(String Name, Abteilung abteilung, int urlaubstage) throws RemoteException {
        Mitarbeiter MA = new Mitarbeiter(Name, abteilung, urlaubstage, 0);
        db.saveMA(MA);
        Mitarbeiter MAnew = this.mitarbeiterLesen(MA.getName());
        String output =  MAnew.toString() + " wurde angelegt.";
        return output;
    }
    
    /**
     * @param ID
     * @return int Urlaubstage
     * @throws RemoteException 
     */
    @Override
    public int urlaubstageLesen(int ID) throws RemoteException {
        return db.readUrlaubstage(ID);
    }
    
    /**
     * @param ID
     * @return Mitarbeiter
     * @throws RemoteException 
     */
    @Override
    public Mitarbeiter mitarbeiterLesen(int ID) throws RemoteException {
        return db.readMA(ID);
    }
    
    /**
     * @param name
     * @return Mitarbeiter
     * @throws RemoteException 
     */
    @Override
    public Mitarbeiter mitarbeiterLesen(String name) throws RemoteException {
        return db.readMA(name);
    }
    
    // Abteilung
    
    /**
     * @param Name
     * @return Abteilung
     */
    @Override
    public Abteilung abteilungLesen(String Name) {
        return db.readDpt(Name);
    }
    
    /**
     * @param abteilungsleiter
     * @param Name
     * @param ID 
     */
    @Override
    public void abteilungAnlegen(Mitarbeiter abteilungsleiter, String Name, int ID) {
        Abteilung AB = new Abteilung(abteilungsleiter, Name, ID);
        db.saveDpt(AB);
        System.out.println("Abteilung " + AB.toString() + " wurde angelegt.");
    }
    
    /**
     * @param Name
     * @return String output
     */
    @Override
    public String abteilungAnlegen(String Name) {
        Abteilung AB = new Abteilung(Name);
        db.saveDpt(AB);
        String output = abteilungLesen(Name).toString() + " wurde angelegt.";
        return output;
    }
    
    /**
     * @param AB_ID
     * @param MA_ID
     * @return String output
     */
    @Override
    public String setAbteilungsleiter(int AB_ID, int MA_ID) {
        db.setAL(AB_ID, MA_ID);
        String output = "Abteilungsleiter " + MA_ID + " wurde der Abteilung " + AB_ID + " zugewiesen.";
        return output;    
    }
    
    // Urlaubsantrag
    
    /**
     * @param ID
     * @return Urlaubsantrag
     * @throws RemoteException 
     */
    @Override
    public Urlaubsantrag urlaubsantragLesen(int ID) throws RemoteException {
        return db.readUA(ID);
    }
    /**
     * @param MA_ID
     * @return ArrayList<Integer>
     * @throws RemoteException 
     */
    @Override
    public ArrayList<Integer> readAllUAsForMA(int MA_ID) throws RemoteException {
        return db.readAllUAsForMA(MA_ID);
    }
    
    /**
     * @param ID
     * @param genehmigt
     * @return String ua.ToString()
     * @throws RemoteException 
     */
    @Override
    public String urlaubsantragEntscheiden(int ID, boolean genehmigt) throws RemoteException {
        Urlaubsantrag ua = db.readUA(ID);
        String ret;
        if (genehmigt) {
            ua.genehmigen(); // sets genehmigt = true;
            ret = "genehmigt";
            db.updateMA(ua.getMA().getID(), ua.getMA().getUrlaubstage());
        } else {
            ua.ablehnen(); // sets genehmigt = false;
            ret = "abgelehnt";
        }
        db.updateUA(ID, genehmigt);
       return ua.toString() + " wurde " + ret + ".";
    }
    
    /**
     * @param MA
     * @param vertreter
     * @param urlaubsbeginn
     * @param urlaubsende
     * @return String output
     * @throws RemoteException 
     */
    @Override
    public String urlaubsantragStellen(Mitarbeiter MA, Mitarbeiter vertreter, Date urlaubsbeginn, Date urlaubsende) throws RemoteException {
        Urlaubsantrag ua = new Urlaubsantrag(MA, vertreter, urlaubsbeginn, urlaubsende, 0);
        db.saveUA(ua);
        String output = "";
        ArrayList<Integer> UAs = db.readAllUAsForMA(MA.getID());
        Urlaubsantrag u = this.urlaubsantragLesen(UAs.get(0));
        if(u.getVertreter() != null){
            output = u.toString() + " wurde gestellt und angenommen.";   
            db.updateMA(MA.getID(),MA.getUrlaubstage());
        }
        else{
            output = u.toString() + " muss entschieden werden, kein Vertreter vorhanden. Angrag mit J annnehmen oder mit N ablehnen.";
        }
        return output;
    }

    public UVImpl() throws RemoteException {
        this.db = new DBConnect();
    }
}
