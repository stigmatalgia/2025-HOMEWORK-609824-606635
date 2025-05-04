package it.uniroma3.diadia;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPosa implements Comando {
	private String attrezzo;
	private IOConsole IO;
	private Partita partita;
	
	public ComandoPosa(IOConsole IO, Partita partita) {
		this.IO = IO;
		this.partita = partita;
	}

	@Override
	public void setParametro(String parametro) {
		this.attrezzo = parametro;

	}

	@Override
	public void esegui(Partita partita) {
		Attrezzo attrezzoDaPosare = this.partita.getGiocatore().getBorsa().removeAttrezzo(this.attrezzo);
        if (attrezzoDaPosare != null) {
            this.partita.getLabirinto().getStanzaCorrente().addAttrezzo(attrezzoDaPosare);
            IO.mostraMessaggio("Hai posato: " + attrezzoDaPosare.getNome());
        } else {
        	IO.mostraMessaggio("Non hai questo attrezzo nella borsa.");
        }

	}

}
