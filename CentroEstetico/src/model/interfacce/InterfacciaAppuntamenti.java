package model.interfacce;

import model.Cliente;
import model.servizi.Servizio;

public interface InterfacciaAppuntamenti {
    Cliente getCliente();
    Servizio getServizio();
    void modificaData(String nuovaData, String nuovaOra);
    String getOra();
    String getData();
    String toString();
}
