package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoFine extends AbstractComando implements Comando{
	
	public ComandoFine() {
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
}
