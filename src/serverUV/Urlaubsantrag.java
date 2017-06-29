package serverUV;

import java.io.Serializable;
import java.sql.Date;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author TKulhavy
 */
public class Urlaubsantrag implements Serializable {

    private final int ID;
    private Mitarbeiter vertreter;
    private Mitarbeiter MA;
    private final Date urlaubsbeginn;
    private final Date urlaubsende;
    private int diffInDays;
    private boolean genehmigt;

    public boolean isGenehmigt() {
        return genehmigt;
    }

    public void genehmigen() {
        if (this.diffInDays > MA.getUrlaubstage()) {
            throw new IllegalArgumentException("Der eingereichte Urlaub überschreitet die verfügbare Anzahl von Urlaubstagen! (Verfügbar: " + MA.getUrlaubstage() + ", Angefordert: " + this.diffInDays + ")");
        } else {
            this.genehmigt = true;
            MA.setUrlaubstage(MA.getUrlaubstage() - diffInDays);
        }
    }

    public void ablehnen() {
        this.genehmigt = false;
    }

    public Date getUrlaubsende() {
        return urlaubsende;
    }

    public Date getUrlaubsbeginn() {
        return urlaubsbeginn;
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

    public Urlaubsantrag(Mitarbeiter MA, Mitarbeiter vertreter, Date urlaubsbeginn, Date urlaubsende, int ID) {
        this.vertreter = vertreter;
        this.urlaubsbeginn = urlaubsbeginn;
        this.urlaubsende = urlaubsende;
        this.ID = ID;
        this.MA = MA;
        if (MA == null) {
            throw new IllegalArgumentException("Bitte geben Sie einen Mitarbeiter für den Urlaubsantrag ein!");
        }
        if (urlaubsbeginn == null || urlaubsende == null) {
            throw new IllegalArgumentException("Bitte geben Sie ein Start- und Enddatum für den Urlaubsantrag ein!");
        }
        long diff = urlaubsende.getTime() - urlaubsbeginn.getTime();
        this.diffInDays = (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        if (this.diffInDays > MA.getUrlaubstage()) {
            throw new IllegalArgumentException("Der eingereichte Urlaub überschreitet die verfügbare Anzahl von Urlaubstagen!");
        }
        if (vertreter != null && vertreter.equals(MA)) {
            throw new IllegalArgumentException("Ein Mitarbeiter kann sich nicht selbst vertreten!");
        } else if (vertreter != null) {
            this.genehmigt = true;
            MA.setUrlaubstage(MA.getUrlaubstage() - this.diffInDays);
        }
    }
}
