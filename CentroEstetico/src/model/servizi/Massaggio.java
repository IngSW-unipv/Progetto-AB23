package model.servizi;

public class Massaggio extends Servizio {
    private int durata;

    public Massaggio(Enum tipologia, double prezzoAlMinuto, int durataMin) {
        super(tipologia, prezzoAlMinuto, durataMin);
    }

    @Override
    public double calcolaCosto() {
        return super.getPrezzo() * this.durata;
    }

    public String toString() {
        return "Il servizio di manicure viene proposto al costo di €" + calcolaCosto();
    }
}
