package src.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import src.model.centro_estetico.CentroEstetico;
import src.view.HomePage;
import src.view.ListaAppuntamentiView;
import src.view.PrenotazioneView;

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
				
			    accediSezionePrenotazione();
				
			}
			
			private void accediSezionePrenotazione() {
				try {
					new ControllerPrenotazione(new PrenotazioneView(), model);
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
			 
				accediSezioneAppuntamenti();
				
			}
			
			private void accediSezioneAppuntamenti() {
				try {
					new ControllerListaAppuntamenti(new ListaAppuntamentiView(), model);
					view.dispose();
				} catch (IOException e1) {
					System.err.println("File non trovato");
					e1.printStackTrace();
				}
			}
		});
		
	}
	
	
}
