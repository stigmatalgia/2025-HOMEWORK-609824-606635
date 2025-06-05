package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class ComandoRegala extends AbstractComando implements Comando{
	private static final String MESSAGGIO_CON_CHI = "Cosa vuoi regalare?...";
	private String messaggio;
	private String attrezzo;
	
	public ComandoRegala(){
	}
	
	@Override
	public void setParametro(String attrezzo) {
		this.attrezzo = attrezzo;
	}
	
	@Override
	public void esegui(Partita partita, IO IO) {
		AbstractPersonaggio personaggio = partita.getStanzaCorrente().getPersonaggio();
		if (personaggio!=null && partita.getGiocatore().getBorsa().getAttrezzo(this.attrezzo) != null) {
			this.messaggio = personaggio.riceviRegalo(partita.getGiocatore().getBorsa().getAttrezzo(this.attrezzo),partita);
			partita.getGiocatore().getBorsa().removeAttrezzo(attrezzo);
			IO.mostraMessaggio(this.messaggio);

		} else IO.mostraMessaggio(MESSAGGIO_CON_CHI);
	}
	
	@Override
	public String getNome() {
		return "Regala";
	}
	
	@Override
	public String getParametro() {
		return this.attrezzo;
	}
	public String getMessaggio() {
		return this.messaggio;
	}
}
