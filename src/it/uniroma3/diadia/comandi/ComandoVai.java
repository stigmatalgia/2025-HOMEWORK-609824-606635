package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoVai extends AbstractComando implements Comando{
	private Direzione direzione;
	
	public ComandoVai() {
	}
	
	@Override
	public void setParametro(String parametro) {
		try {
	        this.direzione = Direzione.fromString(parametro);
	    } catch (IllegalArgumentException e) {
	        this.direzione = null;
	    }
	}
	
	@Override
	public void esegui(Partita partita, IO IO) {
		if(direzione==null) {
			IO.mostraMessaggio("Dove vuoi andare ?");
			return;
		}
		Stanza prossimaStanza = null;
		prossimaStanza = partita.getStanzaCorrente().getStanzaAdiacente(direzione);
		if (prossimaStanza == null)
			IO.mostraMessaggio("Direzione non valida");
		else {
			partita.setStanzaCorrente(prossimaStanza);
			int cfu = partita.getGiocatore().getCfu();
			partita.getGiocatore().setCfu(--cfu);
			if((partita.getGiocatore().getCfu() <= 0) && (prossimaStanza != partita.getLabirinto().getStanzaVincente())){ partita.setFinita();}
			IO.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
		}
	}

	@Override
	public String getNome() {
		return "vai";
	}

	@Override
	public String getParametro() {
		return this.direzione.toString();
	}
	
	
}
