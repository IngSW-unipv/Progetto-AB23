package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import model.centro_estetico.CentroEstetico;
import view.HomePage;
import view.ListaAppuntamentiView;
import view.PrenotazioneView;

public class ControllerHomePage{

	private final HomePage view;
	private final CentroEstetico model;
	
	public ControllerHomePage(HomePage view, CentroEstetico model) {
		this.view = view;
		this.model = model;
		this.setListeners();
	}
	
	public void setListeners() {
		
		// Pulsante "Prenota"
		
		view.getBtnPrenota().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				accediSezioneAppuntamento();

			}
			
			private void accediSezioneAppuntamento() {
				try {
					GestoreNavigazione nav = new GestoreNavigazione();
					nav.navigaAllaPrenotazione(model);
					view.dispose();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			
		});
		
		// Pulsante "I miei Appuntamenti"
		
		view.getBtnListaAppuntamenti().addActionListener(new ActionListener() {
				
			@Override
			public void actionPerformed(ActionEvent e) {
			
					visualizzaAppuntamentiPrenotati();
			
			}
			
			private void visualizzaAppuntamentiPrenotati() {
				try {
					GestoreNavigazione nav = new GestoreNavigazione();
					nav.navigaAllaListaAppuntamenti(model);
					view.dispose();
				} catch (IOException e1) {
					System.err.println("File non trovato");
					e1.printStackTrace();
				}
				
			}
			
		});
		
	}
	
	
}
