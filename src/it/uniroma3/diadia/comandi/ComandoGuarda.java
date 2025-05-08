package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoGuarda implements Comando {
	
	public ComandoGuarda() {
	}
	@Override
	public void setParametro(String parametro) {
		return;

	}

	@Override
	public void esegui(Partita partita, IO IO) {
		IO.mostraMessaggio(partita.getLabirinto().getStanzaCorrente().getDescrizione()+"\nCFU rimasti:" + partita.getGiocatore().getCfu() + "");
	}
	@Override
	public String getNome() {
		return "guarda";
	}
	@Override
	public String getParametro() {
		return null;
	}

}
