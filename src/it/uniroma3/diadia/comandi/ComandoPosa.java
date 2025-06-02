package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPosa extends AbstractComando implements Comando {
	private String attrezzo; 
	
	public ComandoPosa() {
	}

	@Override
	public void setParametro(String parametro) {
		this.attrezzo = parametro;

	}
 
	@Override
	public void esegui(Partita partita, IO IO) {
		Attrezzo attrezzoDaPosare = partita.getGiocatore().getBorsa().removeAttrezzo(this.attrezzo);
        if (attrezzoDaPosare != null) {
            partita.getStanzaCorrente().addAttrezzo(attrezzoDaPosare);
            IO.mostraMessaggio("Hai posato: " + attrezzoDaPosare.getNome());
        } else {
        	IO.mostraMessaggio("Non hai questo attrezzo nella borsa.");
        }

	}

	@Override
	public String getNome() {
		return "posa";
	}

	@Override
	public String getParametro() {
		return this.attrezzo;
	}

}
