package src.main;

import src.model.Cliente;
import src.model.centro_estetico.CentroEstetico;
import src.model.enums.TipiServizi;
import src.model.servizi.Manicure;
import src.model.servizi.Massaggio;
import src.model.servizi.Pedicure;
import src.model.servizi.Servizio;
import src.view.HomePage;
import java.io.*;
import java.text.ParseException;

import src.controller.ControllerHomePage;

public class Main {
    public static void main(String[] args) throws IOException, ParseException{
        CentroEstetico c = new CentroEstetico("CE");
        Cliente cliente = new Cliente("Mario", "Rossi");
        Cliente cliente2 = new Cliente("Giovanni", "Neri");
        Cliente cliente3 = new Cliente("Paola", "Vivas");

        Servizio s1 = new Manicure(TipiServizi.MANICURE, 10, 30);
        Servizio s2 = new Massaggio(TipiServizi.MASSAGGIO, 2, 60);
        Servizio s3 = new Pedicure(TipiServizi.PEDICURE, 12, 30);
 

        c.aggiungiServizio(s1);
        c.aggiungiServizio(s2);
        c.aggiungiServizio(s3);

        c.prenotaAppuntamento(cliente, s1, "12/12/2027", "15:00");
        c.prenotaAppuntamento(cliente2, s1, "12/12/2027", "11:00");
        c.prenotaAppuntamento(cliente3, s3, "20/03/2024","10:00");

        c.visualizzaAppuntamenti();
    
        try {
        	HomePage view = new HomePage();
        	ControllerHomePage controller = new ControllerHomePage(view, c);
		} catch (FileNotFoundException e) {
			System.err.println("File non trovato");
		}
        
    }
}
