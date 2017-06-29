package serverUV;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Date;
import java.util.ArrayList;

public class UVImpl extends UnicastRemoteObject implements UV {

    DBConnect db;

    @Override
    public void mitarbeiterAnlegen(String Name, Abteilung abteilung, int urlaubstage) throws RemoteException {
        Mitarbeiter MA = new Mitarbeiter(Name, abteilung, urlaubstage, 0);
        db.saveMA(MA);
        System.out.println("Mitarbeiter " + MA.toString() + " wurde angelegt.");
    }

    @Override
    public Abteilung abteilungLesen(String Name) {
        return db.readDpt(Name);

    }

    @Override
    public void abteilungAnlegen(Mitarbeiter abteilungsleiter, String Name, int ID) {
        Abteilung AB = new Abteilung(abteilungsleiter, Name, ID);
        db.saveDpt(AB);
        System.out.println("Abteilung " + AB.toString() + " wurde angelegt.");
    }

    @Override
    public void abteilungAnlegen(String Name) {
        Abteilung AB = new Abteilung(Name);
        db.saveDpt(AB);
        System.out.println("Abteilung " + AB.toString() + " wurde angelegt.");
    }

    @Override
    public void setAbteilungsleiter(int AB_ID, int MA_ID) {
        db.setAL(AB_ID, MA_ID);
        System.out.println("Abteilungsleiter " + MA_ID + " wurde der Abteilung " + AB_ID + " zugewiesen.");
    }

    @Override
    public int urlaubstageLesen(int ID) throws RemoteException {
        return db.readUrlaubstage(ID);
    }

    @Override
    public Mitarbeiter mitarbeiterLesen(int ID) throws RemoteException {
        return db.readMA(ID);
    }

    @Override
    public Mitarbeiter mitarbeiterLesen(String name) throws RemoteException {
        return db.readMA(name);
    }

    @Override
    public Urlaubsantrag urlaubsantragLesen(int ID) throws RemoteException {
        return db.readUA(ID);
    }

    @Override
    public ArrayList<Urlaubsantrag> readAllUAsForMA(int MA_ID) throws RemoteException {
        return db.readAllUAsForMA(MA_ID);
    }

    @Override
    public void urlaubsantragEntscheiden(int ID, boolean genehmigt) throws RemoteException {
        Urlaubsantrag ua = db.readUA(ID);
        String ret;
        if (genehmigt) {
            ua.genehmigen();
            ret = "genehmigt";
            db.updateMA(ua.getMA().getID(), ua.getMA().getUrlaubstage());
        } else {
            ua.ablehnen();
            ret = "abgelehnt";
        }
        db.updateUA(ID, genehmigt);
        System.out.println(ua.toString() + " wurde " + ret + ".");
    }

    @Override
    public void urlaubsantragStellen(Mitarbeiter MA, Mitarbeiter vertreter, Date urlaubsbeginn, Date urlaubsende) throws RemoteException {
        Urlaubsantrag ua = new Urlaubsantrag(MA, vertreter, urlaubsbeginn, urlaubsende, 0);
        db.saveUA(ua);
        System.out.println(ua.toString() + " wurde gestellt.");
    }

    public UVImpl() throws RemoteException {
        this.db = new DBConnect();
    }
}
