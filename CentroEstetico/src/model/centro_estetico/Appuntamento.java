package model.centro_estetico;

import model.Cliente;
import model.interfacce.InterfacciaAppuntamenti;
import model.servizi.Servizio;

public class Appuntamento implements InterfacciaAppuntamenti {
    private Cliente cliente;
    private Servizio servizio;
    private String data;
    private String ora;

    public Appuntamento(Cliente cliente, Servizio servizio, String data, String ora) {
        this.cliente = cliente;
        this.servizio = servizio;
        this.data = data;
        this.ora = ora;
    }

    public void modificaData(String nuovaData, String nuovaOra) {
        this.data = nuovaData;
        this.ora = nuovaOra;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Servizio getServizio() {
        return servizio;
    }

    public String getOra() {
        return ora;
    }

    public String getData() {
        return data;
    }

    @Override
    public String toString() {
        return "Cliente: " + cliente + " | Appuntamento per " + servizio.getTipologia() + " in data " + data + " alle ore " + ora + ".";
    }
}
