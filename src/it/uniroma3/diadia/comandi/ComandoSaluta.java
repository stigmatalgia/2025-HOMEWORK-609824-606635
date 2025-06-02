package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class ComandoSaluta extends AbstractComando implements Comando{
	private static final String MESSAGGIO_CON_CHI = "Chi dovrei salutare?...";
	private String messaggio;
	
	public ComandoSaluta(){
	}
	
	@Override
	public void esegui(Partita partita, IO IO) {
		AbstractPersonaggio personaggio = partita.getStanzaCorrente().getPersonaggio();
		if (personaggio!=null) {
			personaggio.saluta();

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
