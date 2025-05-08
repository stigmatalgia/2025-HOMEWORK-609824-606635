package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoFine implements Comando {
	public ComandoFine() {
	}
	@Override
	public void setParametro(String parametro) {
		return;

	}

	@Override
	public void esegui(Partita partita, IO IO) {
		IO.mostraMessaggio("Grazie di aver giocato!");
		partita.setFinita();
	}
	@Override
	public String getNome() {
		return "fine";
	}
	@Override
	public String getParametro() {
		return null;
	}

}
