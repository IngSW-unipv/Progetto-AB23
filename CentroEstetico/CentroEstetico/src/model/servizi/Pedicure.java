package src.model.servizi;

public class Pedicure extends Servizio {

    public Pedicure(Enum tipologia, double prezzo, int durataMin) {
        super(tipologia, prezzo, durataMin);
    }

    public double calcolaCosto() {
        return super.calcolaCosto();
    }


    public String toString() {
        return "Il servizio di pedicure viene proposto al costo di €" + super.getPrezzo();
    }

}
