package controller;

import java.awt.event.*;
import java.io.IOException;
import java.text.ParseException;
import javax.swing.*;
import model.Cliente;
import model.centro_estetico.CentroEstetico;
import model.enums.TipiServizi;
import model.servizi.Servizio;
import view.HomePage;
import view.ListaAppuntamentiView;
import view.PrenotazioneView;

public class ControllerPrenotazione {

	private final PrenotazioneView view;
	private final CentroEstetico model;

	public ControllerPrenotazione(PrenotazioneView view, CentroEstetico model) {
		this.view = view;
		this.model = model;
		this.setListeners();
	}
	
	public void setListeners() {
		/**/
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
				try {
					GestoreNavigazione nav = new GestoreNavigazione();
					nav.navigaAllaHomePage(model);
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
		
		// Pulsante "Conferma Prenotazione"
		
		view.getButtonConferma().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
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
			public boolean controllaInformazioni() {
				
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
				
				int giorno, anno;

				giorno = Integer.parseInt(view.getGiorno().getText());
				anno = Integer.parseInt(view.getAnno().getText());
				
				switch (view.getMese().getText()) {
				case "04":
				case "06":
				case "09":
				case "11":
					if (giorno < 1 || giorno > 30) {
						JOptionPane.showMessageDialog(view, "Non hai inserito una data valida");
						return false;
					}
					break;
				case "01":
				case "03":
				case "05":
				case "07":
				case "08":
				case "10":
				case "12":
					if (giorno < 1 || giorno > 31) {
						JOptionPane.showMessageDialog(view, "Non hai inserito una data valida");
						return false;
					}
					break;
				case "02":
					if (((anno % 4 == 0 && anno % 100 != 0)) || ((anno % 400 == 0))) {
						if (giorno < 1 || giorno > 29) {
							JOptionPane.showMessageDialog(view, "Non hai inserito una data valida");
							return false;
						}
					}else {
						if (giorno < 1 || giorno > 28) {
							JOptionPane.showMessageDialog(view, "Non hai inserito una data valida");
							return false;
						}
						
					}
					break;
				default:
					JOptionPane.showMessageDialog(view, "Non hai inserito una data valida");
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
						GestoreNavigazione nav = new GestoreNavigazione();
						nav.navigaAllaHomePage(model);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					view.dispose();
				}

			}
		});
	}
}
