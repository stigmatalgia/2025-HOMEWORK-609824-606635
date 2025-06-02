package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class ComandoRegala extends AbstractComando implements Comando{
	private static final String MESSAGGIO_CON_CHI = "A chi dovrei fare un regalo?...";
	private String messaggio;
	private String attrezzo;
	
	public ComandoRegala(){
	}
	
	@Override
	public void setParametro(String Attrezzo) {
		this.attrezzo = Attrezzo;
	}
	
	@Override
	public void esegui(Partita partita, IO IO) {
		AbstractPersonaggio personaggio = partita.getStanzaCorrente().getPersonaggio();
		if (personaggio!=null) {
			this.messaggio = personaggio.riceviRegalo(partita.getStanzaCorrente().getAttrezzo(this.attrezzo),partita);
			IO.mostraMessaggio(this.messaggio);

		} else IO.mostraMessaggio(MESSAGGIO_CON_CHI);
	}
	
	@Override
	public String getNome() {
		return "Regala";
	}
	
	public String getMessaggio() {
		return this.messaggio;
	}
}
