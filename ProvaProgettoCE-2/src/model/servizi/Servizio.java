package model.servizi;

public class Servizio {
    private double prezzo;
    private Enum tipologia;
    private int durataMin;

    public Servizio(Enum tipologia, double prezzo, int durataMin) {
        this.tipologia = tipologia;
        this.prezzo = prezzo;
        this.durataMin = durataMin;
    }

    public int getDurata() {
        return durataMin;
    }

    public double calcolaCosto() {
        return this.prezzo;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public Enum getTipologia() {
        return tipologia;
    }
}
