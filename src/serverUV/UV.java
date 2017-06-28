/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverUV;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.Date;

/**
 *
 * @author TKulhavy
 */
public interface UV extends Remote {

    public abstract void mitarbeiterAnlegen(String Name, Abteilung abteilung, int urlaubstage) throws RemoteException;

    public abstract int urlaubstageLesen(int ID) throws RemoteException;

    public abstract void urlaubsantragEntscheiden(int ID, boolean genehmigt) throws RemoteException;

    public abstract Mitarbeiter mitarbeiterLesen(int ID) throws RemoteException;

    public abstract Urlaubsantrag urlaubsantragLesen(int ID) throws RemoteException;

    public abstract void urlaubsantragStellen(Mitarbeiter MA, Mitarbeiter vertreter, Date urlaubsbeginn, Date urlaubsende) throws RemoteException;
}
