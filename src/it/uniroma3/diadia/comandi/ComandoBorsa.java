package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoBorsa extends AbstractComando implements Comando {
	
	public ComandoBorsa() {
	}
	@Override
	public void setParametro(String parametro) {
		return;

	}

	@Override
	public void esegui(Partita partita, IO IO) {
		IO.mostraMessaggio(partita.getGiocatore().getBorsa().toString());
	}
	@Override
	public String getNome() {
		return "borsa";
	}
	@Override
	public String getParametro() {
		return null;
	}

}
