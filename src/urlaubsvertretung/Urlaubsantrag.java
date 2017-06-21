/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package urlaubsvertretung;

import java.util.Date;
import java.util.concurrent.TimeUnit;

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

    public void genehmigen() {
        this.genehmigt = true;
    }

    public void ablehnen() {
        this.genehmigt = false;
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

    @Override
    public String toString() {
        return "Urlaubsantrag { " + "ID = " + ID + ", MA = " + MA + ", urlaubsbeginn = " + urlaubsbeginn + ", urlaubsende = " + urlaubsende + '}';
    }

    public Urlaubsantrag(Mitarbeiter MA, Mitarbeiter vertreter, Date urlaubsbeginn, Date urlaubsende) {
        this.vertreter = vertreter;
        this.MA = MA;
        if (MA == null) {
            throw new IllegalArgumentException("Bitte geben Sie einen Mitarbeiter für den Urlaubsantrag ein!");
        }
        if (vertreter != null && vertreter.equals(MA)) {
            throw new IllegalArgumentException("Ein Mitarbeiter kann sich nicht selbst vertreten!");
        } else {
            this.genehmigt = true;
            long diff = urlaubsende.getTime() - urlaubsbeginn.getTime();
            int diffInDays = (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
            MA.setUrlaubstage(MA.getUrlaubstage() - diffInDays);
        }

        this.urlaubsbeginn = urlaubsbeginn;
        this.urlaubsende = urlaubsende;
        if (urlaubsbeginn == null || urlaubsende == null) {
            throw new IllegalArgumentException("Bitte geben Sie ein Start- und Enddatum für den Urlaubsantrag ein!");
        }
        this.ID = Urlaubsantrag.IDCOUNTER;
        Urlaubsantrag.IDCOUNTER++;

        if (vertreter == null) {
            //Genehmigung erforderlich
            //Constructor schließen, Eingabeaufforderung triggern, evlt aus Comm klasse
        }
    }
}
