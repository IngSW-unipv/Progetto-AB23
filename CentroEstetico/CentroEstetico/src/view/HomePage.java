package src.view;

import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;

public class HomePage extends JFrame{
	
	private final static int IMG_WIDTH= 150;
	private final static int IMG_HEIGHT = 100;
	private final static int WIDTH = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	private final static int HEIGHT = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();;
	private JLabel nome;
	private JLabel titolo;
	private JButton btnPrenota;
	private JButton btnListaAppuntamenti;
	
	public HomePage() throws IOException{
		
		String pathIcon, lookAndFeel, pathMassaggio, pathManicure, pathPedicure;
		JLabel labelMassaggio, labelPedicure, labelManicure, descrizioneSito, testoMassaggio, testoManicure, testoPedicure;
		Container c;
		Font fontTesto;
		Dimension dimBtn;
		File f;
		Image massaggio, pedicure,manicure;
		GridBagConstraints gbc;

		this.setTitle("Centro Estetico");
		this.setSize(WIDTH, HEIGHT);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());

		c = getContentPane();
		
		// Aspetto dei componenti
		
		lookAndFeel = "javax.swing.plaf.nimbus.NimbusLookAndFeel";
		try {
			UIManager.setLookAndFeel(lookAndFeel);
		} catch (Exception e) {
			System.err.println("Classe Look & Feel non trovata / inesistente");
		}		

		// Icona Pagina
		
		pathIcon = "CentroEstetico/src/resource/icona.png";
		f = new File(pathIcon);
		this.setIconImage(ImageIO.read(f));
		
		// Pannello Nord
		
		JPanel pannelloNord = new JPanel();
		pannelloNord.setBackground(new Color(255, 197, 255));
		pannelloNord.setPreferredSize(new Dimension(640, 60));
		pannelloNord.setLayout(new FlowLayout());
		
		nome = new JLabel("Centro Estetico");
		nome.setFont(new Font("Verdana",Font.ITALIC+Font.BOLD,30));
		nome.setForeground(Color.BLACK);
		pannelloNord.add(nome);
		
		c.add(pannelloNord, BorderLayout.NORTH);
		
		// Pannello Laterale
		
		JPanel pannelloLaterale = new JPanel();
		c.add(pannelloLaterale, BorderLayout.WEST);
		
		pannelloLaterale.setBackground(new Color(255, 197, 255));
		pannelloLaterale.setPreferredSize(new Dimension(150, 100));
		pannelloLaterale.setLayout(new FlowLayout());
		
		dimBtn = new Dimension(150, 25);
		
		btnPrenota = new JButton("Prenota");
		btnPrenota.setPreferredSize(dimBtn);
		btnListaAppuntamenti = new JButton("I tuoi appuntamenti");
		btnListaAppuntamenti.setPreferredSize(dimBtn);

		pannelloLaterale.add(btnPrenota);
		pannelloLaterale.add(btnListaAppuntamenti);
		
		// Pannello Centrale
		
		JPanel pannelloCentrale = new JPanel ();
		c.add(pannelloCentrale, BorderLayout.CENTER);
		
		
		pannelloCentrale = new JPanel();
		pannelloCentrale.setLayout(new GridBagLayout());
		gbc = new GridBagConstraints(); 
		pannelloCentrale.setBackground(new Color(180, 255, 255));
			
		titolo = new JLabel("BENVENUTO");
		titolo.setFont(new Font("Courier", Font.ITALIC, 25));
		titolo.setForeground(Color.BLUE);
		
		labelMassaggio = new JLabel();
		labelPedicure = new JLabel();
		labelManicure = new JLabel();
		
		pathMassaggio = "CentroEstetico/src/resource/massaggio.jpeg";
		pathManicure = "CentroEstetico/src/resource/manicure.jpg";
		pathPedicure = "CentroEstetico/src/resource/pedicure.jpg";
				
		massaggio = ImageIO.read(new File(pathMassaggio));
		pedicure = ImageIO.read(new File(pathPedicure));
		manicure = ImageIO.read(new File(pathManicure));
		
		Image scaledMassaggio = massaggio.getScaledInstance(IMG_WIDTH, IMG_HEIGHT, Image.SCALE_SMOOTH);
		Image scaledPedicure = pedicure.getScaledInstance(IMG_WIDTH, IMG_HEIGHT, Image.SCALE_SMOOTH);
		Image scaledManicure = manicure.getScaledInstance(IMG_WIDTH, IMG_HEIGHT, Image.SCALE_SMOOTH);
		
		ImageIcon iconMassaggio = new ImageIcon(scaledMassaggio);
		ImageIcon iconPedicure = new ImageIcon(scaledPedicure);
		ImageIcon iconManicure = new ImageIcon(scaledManicure);
		
		labelMassaggio.setIcon(iconMassaggio);
		labelPedicure.setIcon(iconPedicure);
		labelManicure.setIcon(iconManicure);
		
		testoMassaggio = new JLabel("Massaggio");
		testoManicure = new JLabel("Manicure");
		testoPedicure = new JLabel("Pedicure");
		
		descrizioneSito = new JLabel("Il nostro centro estetico offre numerosi servizi tra cui:");
		descrizioneSito.setFont(new Font("Courier", Font.ITALIC, 20));
		
		testoMassaggio = new JLabel(" Massaggio");
		testoManicure = new JLabel(" Manicure");
		testoPedicure = new JLabel(" Pedicure");
		
		fontTesto = new Font("Serif", Font.ITALIC, 35);
		
		testoMassaggio.setFont(fontTesto);
		testoManicure.setFont(fontTesto);
		testoPedicure.setFont(fontTesto);
		
		gbc.insets = new Insets(5,5,5,5);
				
		// Titolo principale
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		pannelloCentrale.add(titolo, gbc);
		
		// Sottotitolo
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 3;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		pannelloCentrale.add(descrizioneSito, gbc);
		
		// Immagini
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		pannelloCentrale.add(labelMassaggio, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 3;
		pannelloCentrale.add(labelManicure, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 4;
		pannelloCentrale.add(labelPedicure, gbc);
		
		// Testo delle immagini
		
		gbc.gridwidth = 1;
		
		gbc.gridx = 1;
		gbc.gridy = 2;
		pannelloCentrale.add(testoMassaggio, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 3;
		
		pannelloCentrale.add(testoManicure, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 4;
		pannelloCentrale.add(testoPedicure, gbc);
		
		c.add(pannelloNord, BorderLayout.NORTH);
		c.add(pannelloLaterale, BorderLayout.WEST);
		c.add(pannelloCentrale, BorderLayout.CENTER);
		
		this.setVisible(true);
	}
	
	public JButton getBtnPrenota() {
		return btnPrenota;
	}

	public JButton getBtnListaAppuntamenti() {
		return btnListaAppuntamenti;
	}

}
