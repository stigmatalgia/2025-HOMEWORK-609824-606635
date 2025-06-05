package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.FabbricaDiComandiRiflessiva;

import java.io.FileReader;
import java.io.IOException;

import it.uniroma3.diadia.ambienti.Direzione;

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

	private Partita partita;
	private IO IO;

	public DiaDia(IO IO, Labirinto labirinto) {
		this.IO = IO; 
		this.partita = new Partita(labirinto);
	}

	public void gioca() {
		String istruzione; 
		IO.mostraMessaggio(MESSAGGIO_BENVENUTO);	
		do		
			istruzione = IO.leggiRiga();
		while (!processaIstruzione(istruzione));
	} 

	public Partita getPartita() {
		return this.partita;
	}

	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
		//System.out.println("A".repeat(8192));
		Comando comandoDaEseguire;
		FabbricaDiComandiRiflessiva factory = new FabbricaDiComandiRiflessiva();

		comandoDaEseguire = (Comando) factory.costruisciComando(istruzione);
		comandoDaEseguire.esegui(this.partita,IO);
		if(this.partita.vinta())
			IO.mostraMessaggio("Hai vinto!");
		if(this.partita.getGiocatore().getCfu() <= 0)
			IO.mostraMessaggio("Hai esaurito i CFU...");
		return this.partita.isFinita();
	}

	public static void main(String[] args) {
	    try (IOConsole io = new IOConsole();
	         FileReader fileReader = new FileReader("Labirinto.txt")) {

	        CaricatoreLabirinto cl = new CaricatoreLabirinto(fileReader);
	        cl.carica();
	        Labirinto labirinto = cl.getBuilder().getLabirinto();

	        DiaDia gioco = new DiaDia(io, labirinto);
	        gioco.gioca();

	    } catch (FormatoFileNonValidoException e) {
	        System.err.println("Errore nel formato del file di labirinto: " + e.getMessage());
	    } catch (IOException e) {
	        System.err.println("Errore di I/O nella lettura del file: " + e.getMessage());
	    }
	}

}