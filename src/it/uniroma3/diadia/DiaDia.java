package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.0.0..0 
 * Per giocare crea un'istanza di questa classe e invoca il letodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author  docente di POO 
 *         (da un'idea di Michael Kolling and David J. Barnes) 
 *          
 * @version base
 */

public class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";
	
	static final private String[] elencoComandi = {"vai", "aiuto", "fine", "prendi", "posa"};

	private Partita partita;
	private IOConsole IO;

	public DiaDia(IOConsole IO) {
		this.IO = IO; 
		this.partita = new Partita();
	}

	public void gioca() {
		String istruzione; 
		IO.mostraMessaggio(MESSAGGIO_BENVENUTO);	
		do		
			istruzione = IO.leggiRiga();
		while (!processaIstruzione(istruzione));
	}   


	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
		//System.out.println("A".repeat(8192));
	    Comando comandoDaEseguire = new Comando(istruzione);
	    String nomeComando = comandoDaEseguire.getNome();
	    String parametro = comandoDaEseguire.getParametro();
	    
	    if(nomeComando == null){IO.mostraMessaggio("Nessun comando inserito!"); return false;}
	    
	    if (nomeComando.equals("fine")) {
	        this.fine();
	        return true;
	    } 
	    else if (nomeComando.equals("vai")) {
	        this.vai(parametro);
	    } 
	    else if (nomeComando.equals("aiuto")) {
	        this.aiuto();
	    } 
	    else if (nomeComando.equals("prendi")) {
	        Attrezzo attrezzoDaPrendere = this.partita.getLabirinto().getStanzaCorrente().getAttrezzo(parametro);
	        if (attrezzoDaPrendere != null) {
	            if (this.partita.getGiocatore().getBorsa().addAttrezzo(attrezzoDaPrendere)) {
	                this.partita.getLabirinto().getStanzaCorrente().removeAttrezzo(attrezzoDaPrendere);
	                IO.mostraMessaggio("Hai preso: " + attrezzoDaPrendere.getNome());
	            } else {
	            	IO.mostraMessaggio("La borsa è piena!");
	            }
	        } else {
	        	IO.mostraMessaggio("Attrezzo non trovato.");
	        }
	    } 
	    else if (nomeComando.equals("posa")) {
	        Attrezzo attrezzoDaPosare = this.partita.getGiocatore().getBorsa().removeAttrezzo(parametro);
	        if (attrezzoDaPosare != null) {
	            this.partita.getLabirinto().getStanzaCorrente().addAttrezzo(attrezzoDaPosare);
	            IO.mostraMessaggio("Hai posato: " + attrezzoDaPosare.getNome());
	        } else {
	        	IO.mostraMessaggio("Non hai questo attrezzo nella borsa.");
	        }
	    } 
	    else {
	    	IO.mostraMessaggio("Comando sconosciuto");
	    }
	    
	    if (this.partita.vinta()) {
	    	IO.mostraMessaggio("Hai vinto!");
	        return true;
	    }
	    else if(this.partita.isFinita()) {
	    	IO.mostraMessaggio("Hai perso!");
	        return true;
	    }
	    return false;
	}


	// implementazioni dei comandi dell'utente:

	/**
	 * Stampa informazioni di aiuto.
	 */
	private void aiuto() {
		String s = "";
		for(int i=0; i< elencoComandi.length; i++) {
			s+=elencoComandi[i]+" ";}
		IO.mostraMessaggio(s);
		IO.mostraMessaggio("");
	}

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
	 */
	private void vai(String direzione) {
		if(direzione==null)
			IO.mostraMessaggio("Dove vuoi andare ?");
		Stanza prossimaStanza = null;
		prossimaStanza = this.partita.getLabirinto().getStanzaCorrente().getStanzaAdiacente(direzione);
		if (prossimaStanza == null)
			IO.mostraMessaggio("Direzione inesistente");
		else {
			this.partita.getLabirinto().setStanzaCorrente(prossimaStanza);
			int cfu = this.partita.getGiocatore().getCfu();
			this.partita.getGiocatore().setCfu(--cfu);
			if((this.partita.getGiocatore().getCfu() <= 0) && (prossimaStanza != this.partita.getLabirinto().getStanzaVincente())){ this.partita.setFinita();}
		}
		IO.mostraMessaggio(partita.getLabirinto().getStanzaCorrente().getDescrizione());
	}

	/**
	 * Comando "Fine".
	 */
	private void fine() {
		IO.mostraMessaggio("Grazie di aver giocato!");  // si desidera smettere
	}

	public static void main(String[] argc) {
		IOConsole io = new IOConsole();
		DiaDia gioco = new DiaDia(io);
		gioco.gioca();
	}
}