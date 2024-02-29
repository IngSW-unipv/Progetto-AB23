package src.model.centro_estetico;

import src.db.Jdbc;
import src.model.Cliente;
import src.model.eccezioni.DateNonCorrette;
import src.model.interfacce.InterfacciaAppuntamenti;
import src.model.servizi.Servizio;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class CentroEstetico {
    private String nome;
    private Set<Servizio> servizi;
    private ArrayList<InterfacciaAppuntamenti> appuntamenti;
    private Jdbc jdbc;


    public CentroEstetico(String nome) {
        this.nome = nome;
        this.servizi = new HashSet<>();
        this.appuntamenti = new ArrayList<>();
        this.jdbc = new Jdbc();
    }
    
    public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void aggiungiServizio(Servizio servizio) {
        servizi.add(servizio);

        /*if(servizi.contains(servizio)) {
            System.out.println("Servizio aggiornato!");
        } else {
            System.out.println("Servizio aggiunto");
        }*/
    }


    public void visualizzaServizi(){
        for (Servizio s : servizi) {
            System.out.println(s);
        }
    }


    public void rimuoviServizio(Servizio servizio) {
        if(servizi.contains(servizio)) {
            servizi.remove(servizio);
        } else {
            System.out.println("Impossibile rimuovere! Servizio non presente.");
        }
    }

    /*private*/
    public boolean checkData(String dataPrenotazione) throws ParseException {
        Date dataOdierna = new Date();
        Date dataPrenotata = new SimpleDateFormat("dd/MM/yyyy").parse(dataPrenotazione);

        return dataPrenotata.after(dataOdierna);
    }


    private boolean checkAppuntamento(Cliente cliente, Servizio servizio, String data, String ora) {
        for(InterfacciaAppuntamenti appuntamento : appuntamenti) {
            if(appuntamento.getCliente() == cliente && appuntamento.getServizio() == servizio) {
                if(appuntamento.getData().equals(data)) {
                    return false;
                } else {
                    return true;
                }
            }
        }
        return false;
    }
    

    private String randomString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 8) {
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }
    
    public boolean verificaAppuntamentiGiaPrenotati(Cliente cliente, Servizio servizio, String data, String ora) {
        for(InterfacciaAppuntamenti appuntamento : appuntamenti) {
            if(appuntamento.getCliente().getNome().equals(cliente.getNome()) && 
            		appuntamento.getCliente().getCognome().equals(cliente.getCognome()) && 
            		appuntamento.getServizio().getTipologia().equals(servizio.getTipologia()) && 
            		appuntamento.getData().equals(data) && appuntamento.getOra().equals(ora)) {
                    return false;
            }
        }
        return true;
    }

    public void prenotaAppuntamento(Cliente cliente, Servizio servizio, String data, String ora) throws ParseException {
        try {
            if(checkData(data) && !checkAppuntamento(cliente, servizio, data, ora)) {
                appuntamenti.add(new Appuntamento(cliente, servizio, data, ora));
                jdbc.insert(randomString(), cliente.getNome() + " " + cliente.getCognome(), String.valueOf(servizio.getPrezzo()));
                System.out.println("Appuntamento inserito.");
                visualizzaAppuntamenti();
            } else {
                throw new DateNonCorrette();
            }
        } catch (DateNonCorrette d) {
            System.err.println("Le date inserite non sono compatibili! Riprova.");
        }
    }


    public void rimuoviAppuntamento(Cliente cliente, String data) {
        appuntamenti.removeIf(a -> a.getCliente() == cliente && a.getData().equals(data));
    }


    public void modificaAppuntamento(Cliente cliente, Servizio servizio, String nuovaData, String nuovaOra) {
        for(InterfacciaAppuntamenti appuntamento : appuntamenti) {
            if(appuntamento.getCliente() == cliente && appuntamento.getServizio() == servizio) {
                appuntamento.modificaData(nuovaData, nuovaOra);
            }
        }
    }


    public void visualizzaAppuntamenti(String nomeCliente, String cognomeCliente) {
        for (InterfacciaAppuntamenti a : appuntamenti) {
            if(a.getCliente().getNome().equals(nomeCliente) && a.getCliente().getCognome().equals(cognomeCliente)) {
                System.out.println(a);
            }
        }
    }
    
    public ArrayList<InterfacciaAppuntamenti> trovaAppuntamento(String nome, String cognome) {
    	ArrayList<InterfacciaAppuntamenti> listaApp = new ArrayList<>();
    	for (InterfacciaAppuntamenti app: appuntamenti) {
			if (app.getCliente().getNome().equals(nome) && app.getCliente().getCognome().equals(cognome)) {
				listaApp.add(app);
			}
		}
    	return listaApp;
    }
    
    public ArrayList<InterfacciaAppuntamenti> mostraAppuntamenti() {
    	ArrayList<InterfacciaAppuntamenti> listaApp = new ArrayList<>();
    	for (InterfacciaAppuntamenti app: appuntamenti) {
			listaApp.add(app);
		}
    	return listaApp;
    }

    public void visualizzaAppuntamenti() {
        for (InterfacciaAppuntamenti a : appuntamenti) {
            System.out.println(a);
        }
    }
}
