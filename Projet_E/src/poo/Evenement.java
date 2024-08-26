package poo;

import java.util.Date;

public class Evenement {
    private String texte;
    private Date dateEvent;

    public Evenement(String texte, Date dateEvent) {
        this.texte = texte;
        this.dateEvent = dateEvent;
    }

    public String getTexte() {
        return texte;
    }

    public Date getDateEvent() {
        return dateEvent;
    }

    @Override
    public String toString() {
        return dateEvent + ": " + texte;
    }
}
