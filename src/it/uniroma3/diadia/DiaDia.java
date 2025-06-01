package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.comandi.AbstractComando;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.FabbricaDiComandiRiflessiva;

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
		FabbricaDiComandiRiflessiva factory = new FabbricaDiComandiRiflessiva(IO, partita);

		comandoDaEseguire = (Comando) factory.costruisciComando(istruzione);
		comandoDaEseguire.esegui(this.partita,IO);
		if(this.partita.vinta())
			IO.mostraMessaggio("Hai vinto!");
		if(this.partita.getGiocatore().getCfu() <= 0)
			IO.mostraMessaggio("Hai esaurito i CFU...");
		return this.partita.isFinita();
	}

	public static void main(String[] argc) {
		IO io = new IOConsole();
		Labirinto labirinto = new LabirintoBuilder()
                .addStanzaIniziale("Atrio").addAttrezzo("osso", 1)
                .addStanzaBuia("Aula N11", "lanterna").addAttrezzo("chiave", 1)
                .addStanza("Aula N10").addAttrezzo("lanterna", 3)
                .addStanzaMagica("Laboratorio Campus", 1)
                .addStanzaBloccata("Biblioteca", "nord", "chiave")
                .addAdiacenza("Atrio", "Biblioteca", "nord")
                .addAdiacenza("Atrio", "Aula N11", "est")
                .addAdiacenza("Atrio", "Aula N10", "sud")
                .addAdiacenza("Atrio", "Laboratorio Campus", "ovest")
                .addAdiacenza("Aula N11", "Laboratorio Campus", "est")
                .addAdiacenza("Aula N11", "Atrio", "ovest")
                .addAdiacenza("Aula N10", "Atrio", "nord")
                .addAdiacenza("Aula N10", "Aula N11", "est")
                .addAdiacenza("Aula N10", "Laboratorio Campus", "ovest")
                .addAdiacenza("Laboratorio Campus", "Atrio", "est")
                .addAdiacenza("Laboratorio Campus", "Aula N11", "ovest")
                .addAdiacenza("Biblioteca", "Atrio", "sud")
                .addStanzaVincente("Segreteria")
                .addAdiacenza("Biblioteca", "Segreteria", "nord")
                .getLabirinto();

				DiaDia gioco = new DiaDia(io, labirinto);
		gioco.gioca();
	}
}