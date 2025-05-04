package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoVai implements Comando{
	private String direzione;
	private IOConsole IO;
	private Partita partita;
	
	public ComandoVai(IOConsole IO, Partita partita) {
		this.IO = IO;
		this.partita = partita;
	}
	
	@Override
	public void setParametro(String parametro) {
		this.direzione = parametro;
	}
	
	@Override
	public void esegui(Partita partita) {
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
	
	
}
