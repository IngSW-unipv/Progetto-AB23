package main;

import model.Cliente;
import model.centro_estetico.CentroEstetico;
import model.enums.TipiServizi;
import model.servizi.Manicure;
import model.servizi.Massaggio;
import model.servizi.Servizio;
import view.HomePage;
import java.io.*;
import java.text.ParseException;

import controller.ControllerHomePage;

public class Main {
    public static void main(String[] args) throws IOException, ParseException{
        CentroEstetico c = new CentroEstetico("CE");
        Cliente cliente = new Cliente("Mario", "Rossi");
        Cliente cliente2 = new Cliente("Giovanni", "Neri");

        Servizio s1 = new Manicure(TipiServizi.MANICURE, 10, 30);
        Servizio s2 = new Massaggio(TipiServizi.MASSAGGIO, 2, 60);

        c.aggiungiServizio(s1);
        c.aggiungiServizio(s2);

        c.prenotaAppuntamento(cliente, s1, "12/12/2027", "10:20");
        c.prenotaAppuntamento(cliente2, s1, "12/12/2027", "10:20");

        c.visualizzaAppuntamenti();
    
        try {
        	HomePage view = new HomePage();
        	ControllerHomePage controller = new ControllerHomePage(view, c);
		} catch (FileNotFoundException e) {
			System.err.println("File non trovato");
		}
        
    }
}
