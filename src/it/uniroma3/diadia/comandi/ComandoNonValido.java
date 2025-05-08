package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoNonValido implements Comando{
	
	public ComandoNonValido() {
	}
	
	@Override
	public void setParametro(String parametro) {
		return;
		 
	}

	@Override
	public void esegui(Partita partita, IO IO) {
		IO.mostraMessaggio("Comando non valido!");
	}

	@Override
	public String getNome() {
		return null;
	}

	@Override
	public String getParametro() {
		// TODO Auto-generated method stub
		return null;
	}
}
