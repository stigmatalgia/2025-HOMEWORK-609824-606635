package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoGuarda extends AbstractComando implements Comando{
	
	public ComandoGuarda() {
	}

	@Override
	public void esegui(Partita partita, IO IO) {
		IO.mostraMessaggio(partita.getStanzaCorrente().getDescrizione()+"\nCFU rimasti:" + partita.getGiocatore().getCfu() + "\n"+"nella stanza c'e' un "+partita.getStanzaCorrente().getPersonaggio().getClass().getSimpleName());
	}
	
	@Override
	public String getNome() {
		return "guarda";
	}
}
