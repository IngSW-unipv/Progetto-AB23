package src.controller;

import java.awt.event.*;
import java.io.IOException;
import java.text.ParseException;
import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import src.model.Cliente;
import src.model.centro_estetico.CentroEstetico;
import src.model.enums.TipiServizi;
import src.model.servizi.Servizio;
import src.view.HomePage;
import src.view.ListaAppuntamentiView;
import src.view.PrenotazioneView;

public class ControllerPrenotazione {

	private final PrenotazioneView view;
	private final CentroEstetico model;

	public ControllerPrenotazione(PrenotazioneView view, CentroEstetico model) {
		this.view = view;
		this.model = model;
		this.setListeners();
	}
	
	public void setListeners() {
		
		view.getCosto().setText(String.format("%s", view.getMinutaggio().getSelectedItem()));
		
		view.getMinutaggio().addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				view.getCosto().setText(String.format("%s", view.getMinutaggio().getSelectedItem()));
			}
		});;
		
		// Pulsante "HomePage"
		
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
		
		// Pulsante "I miei Appuntamenti"
		
		view.getBtnListaAppuntamenti().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			      accediAppuntamenti();
				
			}
			private void accediAppuntamenti() {
				try {
					new ControllerListaAppuntamenti(new ListaAppuntamentiView(), model);
					view.dispose();
				} catch (IOException e1) {
					System.err.println("File non trovato");
					e1.printStackTrace();
				}
			}
		});
		
		// Pulsante "Conferma Prenotazione"
		
		view.getButtonConferma().addActionListener(new ActionListener() {
			
			@Override
            public void actionPerformed(ActionEvent e) {
				 prenotazione();
		    }		
			
		    private void prenotazione() {

				String nome, cognome, data, orario;
				int minuti,giorno, mese, anno;
				double costo;
				TipiServizi tipoServizio;
				
				if(controllaInformazioni()) {
					nome = view.getNome().getText();
					cognome = view.getCognome().getText();
					tipoServizio = (TipiServizi)view.getTipologiaServizio().getSelectedItem();
					giorno = Integer.parseInt(view.getGiorno().getText());
					mese = Integer.parseInt(view.getMese().getText());
					anno = Integer.parseInt(view.getAnno().getText());
					data = String.format("%02d", giorno) + "/" + String.format("%02d", mese)+ "/" + String.format("%02d", anno);
					minuti = (int)view.getMinutaggio().getSelectedItem();
					orario = (String)view.getOrario().getSelectedItem();
					costo = Double.parseDouble(view.getCosto().getText());
					
					Cliente c = new Cliente(nome, cognome);
					Servizio s = new Servizio(tipoServizio, costo, minuti);
					
					if (!model.verificaAppuntamentiGiaPrenotati(c, s, data, orario)){
						JOptionPane.showMessageDialog(view, "Appuntamento già presente");
						view.getOrario().setSelectedIndex(0);
					}else{ 
						
						int risposta = JOptionPane.showConfirmDialog(view, "Vuoi confermare la prenotazione", "", JOptionPane.YES_NO_OPTION);
						if (risposta == JOptionPane.YES_OPTION) {
							model.aggiungiServizio(s);
							try {
								model.prenotaAppuntamento(c, s, data, orario);
								JOptionPane.showMessageDialog(view, "Appuntamento Prenotato");
							} catch (ParseException e1) {
								e1.printStackTrace();
							}
						}else
							JOptionPane.showMessageDialog(view, "Appuntamento non prenotato");
						view.getNome().setText("");
						view.getCognome().setText("");
						view.getGiorno().setText("");
						view.getMese().setText("");
						view.getAnno().setText("");
						view.getMinutaggio().setSelectedIndex(0);
						view.getCosto().setText(String.format("%s", view.getMinutaggio().getSelectedItem()));
						view.getOrario().setSelectedIndex(0);
						}
				}
		    }
		    
			private boolean controllaInformazioni() {
				
				if (view.getNome().getText().equals("")) {
					JOptionPane.showMessageDialog(view, "Il campo Nome non può essere vuoto");
					return false;
				}
				
				if (view.getCognome().getText().equals("")) {
					JOptionPane.showMessageDialog(view, "Il campo Cognome non può essere vuoto");
					return false;
				}
				
				if (view.getTipologiaServizio().getSelectedItem().equals("Servizio")){
					JOptionPane.showMessageDialog(view, "Non hai selezionato un servizio valido");
					return false;
				}
				
				if (view.getOrario().getSelectedItem().equals("")) {
					JOptionPane.showMessageDialog(view, "Non hai selezionato un orario valido");
					return false;
				}
				
				if (view.getCosto().getText().equals("")) {
					JOptionPane.showMessageDialog(view, "Non hai inserito un costo valido");
					return false;
				}
				
				return true;
			}
			
		});
			
	
		
		// Pulsante "Annulla Prenotazione"
	
		view.getButtonAnnulla().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				annullaPrenotazione();
				
			}
			private void annullaPrenotazione() {
				int risposta = JOptionPane.showConfirmDialog(view, "Sei sicuro di voler annullare la prenotazione ?", "", JOptionPane.YES_NO_OPTION);
				if (risposta == JOptionPane.YES_OPTION) {
					JOptionPane.showMessageDialog(view, "Prenotazione Annullata");
					try {
						new ControllerHomePage(new HomePage(), model);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					view.dispose();
				}
			}
		});
	}
}
