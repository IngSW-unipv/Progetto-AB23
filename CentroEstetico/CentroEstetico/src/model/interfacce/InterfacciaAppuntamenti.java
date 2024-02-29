package src.model.interfacce;

import src.model.Cliente;
import src.model.servizi.Servizio;

public interface InterfacciaAppuntamenti {
    Cliente getCliente();
    Servizio getServizio();
    void modificaData(String nuovaData, String nuovaOra);
    String getOra();
    String getData();
    String toString();
}
