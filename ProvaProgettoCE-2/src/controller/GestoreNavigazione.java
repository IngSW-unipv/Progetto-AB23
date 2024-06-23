package controller;

import java.io.IOException;
import model.centro_estetico.CentroEstetico;
import view.HomePage;
import view.ListaAppuntamentiView;
import view.PrenotazioneView;

public class GestoreNavigazione {

	public GestoreNavigazione() {
		super();
	}
	
	public void navigaAllaHomePage(CentroEstetico model) throws IOException {
		new ControllerHomePage(new HomePage(), model);
	}
	
	public void navigaAllaListaAppuntamenti(CentroEstetico model) throws IOException {
		new ControllerListaAppuntamenti(new ListaAppuntamentiView(), model);
	}

	public void navigaAllaPrenotazione(CentroEstetico model) throws IOException {
		new ControllerPrenotazione(new PrenotazioneView(), model);
	}
	
}
