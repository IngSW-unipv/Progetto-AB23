package src.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JOptionPane;

import src.model.centro_estetico.CentroEstetico;
import src.model.interfacce.InterfacciaAppuntamenti;
import src.view.HomePage;
import src.view.ListaAppuntamentiView;
import src.view.PrenotazioneView;

public class ControllerListaAppuntamenti{

	private final ListaAppuntamentiView view;
	private final CentroEstetico model;
	
	public ControllerListaAppuntamenti(ListaAppuntamentiView view, CentroEstetico model) {
		this.view = view;
		this.model = model;
		this.setListeners();
	}
	
	public void setListeners() {
		
		// Pulsante HomePage
		
		view.getBtnHome().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				accediHomePage();			
			}
			
			private void accediHomePage() {
				try {
					new ControllerHomePage(new HomePage(), model);
					view.dispose();
				} catch (IOException e1) {
					e1.printStackTrace();
				}	
			}
		});
		
		// Pulsante Pagina Prenotazione
		
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
		
		// Pulsante che mostra tutti gli appuntamenti prenotati
		
		view.getRicercaTuttiAppuntamenti().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				ricercaAppuntamentiPrenotati();
				
			}
				private void ricercaAppuntamentiPrenotati () {
					String nome, cognome, servizio, data, orario;
					
					ArrayList<InterfacciaAppuntamenti> lista = new ArrayList<>();
					lista = model.mostraAppuntamenti();
					
					cancellaRigheTabella();
						
					if (lista.size() > 0) {
						for (int i = 0; i < lista.size(); i++) {
							nome = lista.get(i).getCliente().getNome();
							cognome = lista.get(i).getCliente().getCognome();
							servizio = String.format("%s", lista.get(i).getServizio().getTipologia());
							data = lista.get(i).getData();
							orario = lista.get(i).getOra();
							view.getTable().addRow(new Object[] {nome,cognome,servizio,data, orario});	
						}
					}else 
						JOptionPane.showMessageDialog(view, "Nessun appuntamento prenotato");
				}
		});
		
		// Pulsante che mostra gli appuntamenti di un cliente dato nome e cognome
		
		view.getRicercaAppuntamento().addActionListener(new ActionListener() {
		
			@Override
			public void actionPerformed(ActionEvent e) {
				
			      ricercaAppuntamentiCliente();
			}
			
			private void ricercaAppuntamentiCliente () {
				String nome, cognome, servizio, data, orario;
				ArrayList<InterfacciaAppuntamenti> lista = new ArrayList<>();
				
				cancellaRigheTabella();
				
				if(controlloInfoClienti()){
					nome = view.getNome().getText();
					cognome = view.getCognome().getText();
					lista = model.trovaAppuntamento(nome, cognome);
					
					if (lista.size() > 0) {
						for (int i = 0; i < lista.size(); i++) {
							servizio = String.format("%s", lista.get(i).getServizio().getTipologia());
							data = lista.get(i).getData();
							orario = lista.get(i).getOra();
							view.getTable().addRow(new Object[] {nome,cognome,servizio,data, orario});	
						}
					}else 
						JOptionPane.showMessageDialog(view, "Nessun appuntamento trovato per il cliente " + nome + " " + cognome);
					
				}
			}
		});
	}
	
	public boolean controlloInfoClienti() {
		
		if (view.getNome().getText().equals("")) {
			JOptionPane.showMessageDialog(view, "Nome non inserito");
			return false;
		}
		
		if (view.getCognome().getText().equals("")) {
			JOptionPane.showMessageDialog(view, "Cognome non inserito");
			return false;
		}
		
		return true;
		
	}
	
	public void cancellaRigheTabella() {
		if (view.getTable().getRowCount() > 0) {
		    for (int i = view.getTable().getRowCount() - 1; i > -1; i--) {
		    	view.getTable().removeRow(i);
		    }
		}
	}
	
}
