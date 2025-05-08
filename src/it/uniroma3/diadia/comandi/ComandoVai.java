package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoVai implements Comando{
	private String direzione;
	
	public ComandoVai() {
	}
	
	@Override
	public void setParametro(String parametro) {
		this.direzione = parametro; 
	}
	
	@Override
	public void esegui(Partita partita, IO IO) {
		if(direzione==null) 
			IO.mostraMessaggio("Dove vuoi andare ?");
		Stanza prossimaStanza = null;
		prossimaStanza = partita.getLabirinto().getStanzaCorrente().getStanzaAdiacente(direzione);
		if (prossimaStanza == null)
			IO.mostraMessaggio("Direzione inesistente");
		else {
			partita.getLabirinto().setStanzaCorrente(prossimaStanza);
			int cfu = partita.getGiocatore().getCfu();
			partita.getGiocatore().setCfu(--cfu);
			if((partita.getGiocatore().getCfu() <= 0) && (prossimaStanza != partita.getLabirinto().getStanzaVincente())){ partita.setFinita();}
		}
		IO.mostraMessaggio(partita.getLabirinto().getStanzaCorrente().getDescrizione());
	}

	@Override
	public String getNome() {
		return "vai";
	}

	@Override
	public String getParametro() {
		return this.direzione;
	}
	
	
}
