package view;

import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;

import model.enums.TipiServizi;

public class PrenotazioneView extends JFrame{

	private final static int WIDTH = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	private final static int HEIGHT = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();;
	private JTextField nome;
	private JTextField cognome;
	private JTextField costo;
	private JTextField giorno;
	private JTextField mese;
	private JTextField anno;
	private JComboBox<TipiServizi> tipologiaServizio;
	private JComboBox<String> orario;
	private JComboBox<Integer> minutaggio;
	private JButton buttonConferma;
	private JButton buttonAnnulla;
	private JButton btnListaAppuntamenti;
	private JButton btnHome;
	
	public PrenotazioneView() throws IOException{
		
		String pathIcon, lookAndFeel;
		JLabel titolo, infoCliente, labelNome, labelCognome, infoServizio, labelServizio, infoData, infoCostoMinuti, 
		dataAppuntamento, oraAppuntamento, labelCosto, labelMinutaggio;
		GridBagConstraints gbc;
		JPanel pannelloNord, form, pannelloLaterale, data;
		Dimension textFieldDimensione, dimBtn, dimData;
		File f;
		Font font, titoliSezioniFont;
		
		this.setTitle("Prenotazione Appuntamento CE");
		this.setSize(WIDTH, HEIGHT);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());

		lookAndFeel = "javax.swing.plaf.nimbus.NimbusLookAndFeel";
		try {
			UIManager.setLookAndFeel(lookAndFeel);
		} catch (Exception e) {
			System.err.println("Classe Look & Feel non trovata / inesistente");
		}		
		
		// Icona Pagina
		
		pathIcon = "CentroEstetico\\src\\resource\\icona.png";
		f = new File(pathIcon);
		this.setIconImage(ImageIO.read(f));
		
		// Titolo
		
		pannelloNord = new JPanel();
		pannelloNord.setBackground(new Color(255, 197, 255));
		pannelloNord.setPreferredSize(new Dimension(640, 60));
		titolo = new JLabel("Prenotazione Appuntamento Centro Estetico");
		font = new Font("Verdana", Font.BOLD+Font.ITALIC, 30);
		titolo.setFont(font);
		pannelloNord.add(titolo);
		this.add(pannelloNord, BorderLayout.NORTH);
		
		// Form di Prenotazione

		form = new JPanel();
		form.setBackground(new Color(180, 255, 255));
		form.setLayout(new GridBagLayout());
		gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5); 
		
		// Sezione Dati Cliente
		
		titoliSezioniFont = new Font("Courier", Font.BOLD, 15);
		
		infoCliente = new JLabel("Dati Cliente");
		infoCliente.setFont(titoliSezioniFont);
		labelNome = new JLabel("Nome");
		labelCognome = new JLabel("Cognome");
		
		textFieldDimensione = new Dimension(120, 30);
		nome = new JTextField();
		nome.setPreferredSize(textFieldDimensione);
		cognome = new JTextField();
		cognome.setPreferredSize(textFieldDimensione);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		form.add(infoCliente, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		form.add(labelNome, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		form.add(labelCognome, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		form.add(nome, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 2;
		form.add(cognome, gbc);
		
		// Info Servizio
		
		TipiServizi[] servizi = {TipiServizi.MANICURE, TipiServizi.PEDICURE, TipiServizi.MASSAGGIO};
		
		infoServizio = new JLabel("Informazioni Servizio");
		infoServizio.setFont(titoliSezioniFont);
		labelServizio = new JLabel("Tipologia Servizio");
		tipologiaServizio = new JComboBox<TipiServizi>(servizi);
		
		gbc.gridx = 0;
		gbc.gridy = 3;
		form.add(infoServizio, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 4;
		form.add(labelServizio, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 4;
		form.add(tipologiaServizio, gbc);
		
		// Info Data
		
		String[] orariAppuntamenti = {"", "09:00", "10:00", "11:00", "12:00", "15:00", "16:00", "17:00", "18:00"};
		
		infoData = new JLabel("Informazioni Data");
		infoData.setFont(titoliSezioniFont);
		dataAppuntamento = new JLabel("Data(gg/mm/aaaa)");
		giorno = new JTextField();
		mese = new JTextField();
		anno = new JTextField();
		
		dimData = new Dimension(50, 25);
		
		giorno.setPreferredSize(dimData);
		mese.setPreferredSize(dimData);
		anno.setPreferredSize(dimData);
		
		data = new JPanel();
		data.setLayout(new FlowLayout());
		data.add(giorno);
		data.add(mese);
		data.add(anno);
		
		oraAppuntamento = new JLabel("Orario");
		orario = new JComboBox<String>(orariAppuntamenti);
		
		gbc.gridx = 0;
		gbc.gridy = 5;
		form.add(infoData, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 6;
		form.add(dataAppuntamento, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 6;
		form.add(data, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 7;
		form.add(oraAppuntamento, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 7;
		form.add(orario, gbc);
		
		// Sezione Costo e Minutaggio
		
		Integer[] sceltaMinutaggio = {30, 60, 90, 120};
		
		infoCostoMinuti = new JLabel("Costo e Minutaggio");
		infoCostoMinuti.setFont(titoliSezioniFont);
		labelMinutaggio = new JLabel("Tempo Servizio in minuti");
		minutaggio = new JComboBox<Integer>(sceltaMinutaggio);
		labelCosto = new JLabel("Costo");
		costo = new JTextField();
		costo.setPreferredSize(textFieldDimensione);
		costo.setEditable(false);
		
		gbc.gridx = 0;
		gbc.gridy = 8;
		form.add(infoCostoMinuti, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 9;
		form.add(labelMinutaggio, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 9;
		form.add(minutaggio, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 10;
		form.add(labelCosto, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 10;
		form.add(costo, gbc);
		
		// Sezione button
		
		buttonConferma = new JButton("Conferma Prenotazione");
		buttonAnnulla = new JButton("Annulla Prenotazione");
		
		gbc.gridx = 0;
		gbc.gridy = 11;
		form.add(buttonConferma, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 11;
		form.add(buttonAnnulla, gbc);
		
		// Pannello Laterale
		
		pannelloLaterale = new JPanel();
		pannelloLaterale.setBackground(new Color(255, 197, 255));
		pannelloLaterale.setPreferredSize(new Dimension(150, 100));
		pannelloLaterale.setLayout(new FlowLayout());
		
		dimBtn = new Dimension(150, 25);
		
		btnHome = new JButton("HomePage");
		btnHome.setPreferredSize(dimBtn);
		btnListaAppuntamenti = new JButton("I tuoi appuntamenti");
		btnListaAppuntamenti.setPreferredSize(dimBtn);

		pannelloLaterale.add(btnHome);
		pannelloLaterale.add(btnListaAppuntamenti);
		
		this.add(form, BorderLayout.CENTER);
		this.add(pannelloLaterale, BorderLayout.WEST);
		this.setVisible(true);
		
	}

	public JTextField getNome() {
		return nome;
	}

	public void setNome(JTextField nome) {
		this.nome = nome;
	}

	public JTextField getCognome() {
		return cognome;
	}

	public void setCognome(JTextField cognome) {
		this.cognome = cognome;
	}

	public JTextField getCosto() {
		return costo;
	}

	public void setCosto(JTextField costo) {
		this.costo = costo;
	}

	public JTextField getGiorno() {
		return giorno;
	}

	public JTextField getMese() {
		return mese;
	}

	public JTextField getAnno() {
		return anno;
	}

	public JComboBox<String> getOrario() {
		return orario;
	}

	public void setOrario(JComboBox<String> orario) {
		this.orario = orario;
	}

	public JComboBox<Integer> getMinutaggio() {
		return minutaggio;
	}

	public void setMinutaggio(JComboBox<Integer> minutaggio) {
		this.minutaggio = minutaggio;
	}

	//public JComboBox<String> getTipologiaServizio() {
	public JComboBox<TipiServizi> getTipologiaServizio() {
		return tipologiaServizio;
	}

	public JButton getButtonConferma() {
		return buttonConferma;
	}

	public JButton getButtonAnnulla() {
		return buttonAnnulla;
	}

	public JButton getBtnListaAppuntamenti() {
		return btnListaAppuntamenti;
	}

	public JButton getBtnHome() {
		return btnHome;
	}
}
