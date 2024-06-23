package view;

import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ListaAppuntamentiView extends JFrame{

	private final static int WIDTH = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	private final static int HEIGHT = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();;
	private JButton btnHome;
	private JButton btnPrenota;
	private JTextField nome;
	private JTextField cognome;
	private JLabel labelNome;
	private JLabel labelCognome;
	private JButton ricercaAppuntamento;
	private JButton ricercaTuttiAppuntamenti;
	private JTable tabellaAppuntamenti;
	private DefaultTableModel table;
	
	public ListaAppuntamentiView() throws IOException {
		
		String lookAndFeel, pathIcon;
		JPanel pannelloNord, pannelloLaterale, pannelloCentrale, barraRicerca;
		JLabel titolo;
		Dimension dimBtn, textFieldDimensione;
		File f;
		Font font;
		
		this.setTitle("Centro Estetico");
		this.setSize(WIDTH, HEIGHT);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		
		// Aspetto dei componenti
		
		lookAndFeel = "javax.swing.plaf.nimbus.NimbusLookAndFeel";
		try {
			UIManager.setLookAndFeel(lookAndFeel);
		} catch (Exception e) {
			System.err.println("Classe Look & Feel non trovata / inesistente");
		}
		
		// Icona Pagina
		
		pathIcon = "src\\resource\\icona.png";
		f = new File(pathIcon);
		this.setIconImage(ImageIO.read(f));
		
		pannelloNord = new JPanel();
		pannelloNord.setBackground(new Color(255, 197, 255));
		pannelloNord.setPreferredSize(new Dimension(640, 60));
		titolo = new JLabel("Lista Appuntamenti Clienti");
		font = new Font("Verdana", Font.BOLD+Font.ITALIC, 30);
		titolo.setFont(font);
		pannelloNord.add(titolo);
		this.add(pannelloNord, BorderLayout.NORTH);
		
		// Pannello Laterale
		
		pannelloLaterale = new JPanel();
		pannelloLaterale.setBackground(new Color(255, 197, 255));
		pannelloLaterale.setPreferredSize(new Dimension(150, 100));
		pannelloLaterale.setLayout(new FlowLayout());
		
		dimBtn = new Dimension(150, 25);
		
		btnHome = new JButton("HomePage");
		btnHome.setPreferredSize(dimBtn);
		btnPrenota = new JButton("Prenota");
		btnPrenota.setPreferredSize(dimBtn);

		pannelloLaterale.add(btnHome);
		pannelloLaterale.add(btnPrenota);
		
		this.add(pannelloLaterale, BorderLayout.WEST);
		
		// Pannello Centrale
		
		pannelloCentrale = new JPanel();
		pannelloCentrale.setBackground(new Color(180, 255, 255));
		pannelloCentrale.setLayout(new BorderLayout());
		
		// Barra ricerca appuntamenti
		
		barraRicerca = new JPanel();
		barraRicerca.setBackground(new Color(180, 255, 255));
		barraRicerca.setLayout(new FlowLayout());
		labelNome = new JLabel("Nome");
		nome = new JTextField();
		labelCognome = new JLabel("Cognome");
		cognome = new JTextField();
		ricercaAppuntamento = new JButton("Cerca Appuntamento");
		ricercaTuttiAppuntamenti = new JButton("Visualizza tutti gli appuntamenti");
		
		textFieldDimensione = new Dimension(120, 30);
		nome.setPreferredSize(textFieldDimensione);
		cognome.setPreferredSize(textFieldDimensione);
		ricercaAppuntamento.setPreferredSize(dimBtn);
		ricercaTuttiAppuntamenti.setPreferredSize(new Dimension(200, 25));
		
		barraRicerca.add(labelNome);
		barraRicerca.add(nome);
		barraRicerca.add(labelCognome);
		barraRicerca.add(cognome);
		barraRicerca.add(ricercaAppuntamento);
		barraRicerca.add(ricercaTuttiAppuntamenti);
		
		pannelloCentrale.add(barraRicerca, BorderLayout.NORTH);
		this.add(pannelloCentrale, BorderLayout.CENTER);
		
		// Tabella Appuntamenti
		
		String[] colonneAppuntamenti = {"Nome", "Cognome", "Tipologia Servizio", "Data", "Ora"};
		
		table = new DefaultTableModel(){  
		       public boolean isCellEditable(int row,int column){  
		           if(true) 
		        	   return false;  
		           return true;  
		         }  
		       };
		tabellaAppuntamenti = new JTable(table);
		tabellaAppuntamenti.getTableHeader().setReorderingAllowed(false);
		for (String colonna : colonneAppuntamenti) {
			table.addColumn(colonna);
		}
		JScrollPane scrollPane = new JScrollPane(tabellaAppuntamenti);
		
		pannelloCentrale.add(scrollPane, BorderLayout.CENTER);
		
		this.setVisible(true);
	}

	public JButton getBtnHome() {
		return btnHome;
	}

	public JButton getBtnPrenota() {
		return btnPrenota;
	}

	public JTextField getNome() {
		return nome;
	}

	public JTextField getCognome() {
		return cognome;
	}

	public JButton getRicercaAppuntamento() {
		return ricercaAppuntamento;
	}

	public JTable getTabellaAppuntamenti() {
		return tabellaAppuntamenti;
	}

	public JButton getRicercaTuttiAppuntamenti() {
		return ricercaTuttiAppuntamenti;
	}

	public DefaultTableModel getTable() {
		return table;
	}

}
