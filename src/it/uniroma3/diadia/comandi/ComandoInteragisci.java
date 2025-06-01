package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class ComandoInteragisci extends AbstractComando implements Comando{
	private static final String MESSAGGIO_CON_CHI = "Con chi dovrei interagire?...";
	private String messaggio;
	
	public ComandoInteragisci(){
	}
	
	@Override
	public void esegui(Partita partita, IO IO) {
		AbstractPersonaggio personaggio = partita.getLabirinto().getStanzaCorrente().getPersonaggio();
		if (personaggio!=null) {
			this.messaggio = personaggio.agisci(partita);
			IO.mostraMessaggio(this.messaggio);

		} else IO.mostraMessaggio(MESSAGGIO_CON_CHI);
	}
	
	@Override
	public String getNome() {
		return "Interagisci";
	}
	
	public String getMessaggio() {
		return this.messaggio;
	}
}
