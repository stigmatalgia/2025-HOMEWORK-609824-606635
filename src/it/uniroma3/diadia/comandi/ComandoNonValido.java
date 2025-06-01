package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoNonValido extends AbstractComando implements Comando {
	
	public ComandoNonValido() {
	}

	@Override
	public void esegui(Partita partita, IO IO) {
		IO.mostraMessaggio("Comando non valido!");
	}

	@Override
	public String getNome() {
		return null;
	}
}
