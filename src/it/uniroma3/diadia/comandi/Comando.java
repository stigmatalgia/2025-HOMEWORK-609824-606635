package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public interface Comando {
	abstract public void esegui(Partita partita, IO IO);
	abstract public String getNome();
}
