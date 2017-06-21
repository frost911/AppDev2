/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package urlaubsvertretung;

import java.util.Date;

/**
 *
 * @author TKulhavy
 */
public class Urlaubsantrag {

    private static int IDCOUNTER = 0;
    private final int ID;
    private Mitarbeiter vertreter;
    private Mitarbeiter MA;
    private Date urlaubsbeginn;
    private Date urlaubsende;
    private boolean genehmigt;

    public boolean isGenehmigt() {
        return genehmigt;
    }

    public void setGenehmigt(boolean genehmigt) {
        this.genehmigt = genehmigt;
    }

    public Date getUrlaubsende() {
        return urlaubsende;
    }

    public void setUrlaubsende(Date urlaubsende) {
        this.urlaubsende = urlaubsende;
    }

    public Date getUrlaubsbeginn() {
        return urlaubsbeginn;
    }

    public void setUrlaubsbeginn(Date urlaubsbeginn) {
        this.urlaubsbeginn = urlaubsbeginn;
    }

    public Mitarbeiter getVertreter() {
        return vertreter;
    }

    public void setVertreter(Mitarbeiter vertreter) {
        this.vertreter = vertreter;
    }

    public Mitarbeiter getMA() {
        return MA;
    }

    public void setMA(Mitarbeiter MA) {
        this.MA = MA;
    }

    public int getID() {
        return ID;
    }

    public Urlaubsantrag(Mitarbeiter vertreter, Mitarbeiter MA, Date urlaubsbeginn, Date urlaubsende, boolean genehmigt) {
        this.vertreter = vertreter;
        this.MA = MA;
        this.urlaubsbeginn = urlaubsbeginn;
        this.urlaubsende = urlaubsende;
        this.genehmigt = genehmigt;
        this.ID = Urlaubsantrag.IDCOUNTER;
        Urlaubsantrag.IDCOUNTER++;
    }

}
