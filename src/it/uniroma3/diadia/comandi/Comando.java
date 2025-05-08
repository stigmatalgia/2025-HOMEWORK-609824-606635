package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public interface Comando {
	//set parametro del comando
	public void setParametro(String parametro);
	//esecuzione comando
	public void esegui(Partita partita, IO IO);
	public String getNome();
	public String getParametro();
}
