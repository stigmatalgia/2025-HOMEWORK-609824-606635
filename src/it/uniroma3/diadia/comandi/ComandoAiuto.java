package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoAiuto extends AbstractComando implements Comando{
	static final private String[] elencoComandi = {"vai", "aiuto", "fine", "prendi", "posa", "guarda"};
	
	public ComandoAiuto() {
	}

	@Override
	public void esegui(Partita partita, IO IO) {
		String s = "";
		for(int i=0; i< elencoComandi.length; i++) {
			s+=elencoComandi[i]+" ";}
		IO.mostraMessaggio(s);
	}

	@Override
	public String getNome() {
		return "aiuto";
	}
}
