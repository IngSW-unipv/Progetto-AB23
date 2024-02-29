package src.model.servizi;

public class Manicure extends Servizio {

    public Manicure(Enum tipologia, double prezzo, int durataMin) {
        super(tipologia, prezzo, durataMin);
    }

    public double calcolaCosto() {
        return super.calcolaCosto();
    }

    @Override
    public String toString() {
        return "Il servizio di manicure viene proposto al costo di €" + super.getPrezzo();
    }
}
