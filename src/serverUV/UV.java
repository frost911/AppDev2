/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverUV;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author TKulhavy
 */
public interface UV extends Remote {

    public abstract String mitarbeiterAnlegen(String Name, Abteilung abteilung, int urlaubstage) throws RemoteException;

    public abstract void abteilungAnlegen(Mitarbeiter abteilungsleiter, String Name, int ID) throws RemoteException;

    public abstract String abteilungAnlegen(String Name) throws RemoteException;

    public abstract int urlaubstageLesen(int ID) throws RemoteException;
    
    //public abstract ArrayList gibInput(String input, int ID) throws RemoteException;

    public abstract void urlaubsantragEntscheiden(int ID, boolean genehmigt) throws RemoteException;

    public abstract String setAbteilungsleiter(int AB_ID, int MA_ID) throws RemoteException;

    public abstract Mitarbeiter mitarbeiterLesen(int ID) throws RemoteException;

    public abstract Mitarbeiter mitarbeiterLesen(String name) throws RemoteException;

    public abstract Abteilung abteilungLesen(String Name) throws RemoteException;

    public abstract Urlaubsantrag urlaubsantragLesen(int ID) throws RemoteException;

    public abstract ArrayList<Urlaubsantrag> readAllUAsForMA(int MA_ID) throws RemoteException;

    public abstract String urlaubsantragStellen(Mitarbeiter MA, Mitarbeiter vertreter, Date urlaubsbeginn, Date urlaubsende) throws RemoteException;
}
