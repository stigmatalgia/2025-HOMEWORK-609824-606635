package it.uniroma3.diadia;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPrendi implements Comando{
	private String attrezzo;
	private IOConsole IO;
	private Partita partita;
	
	public ComandoPrendi(IOConsole IO, Partita partita) {
		this.IO = IO;
		this.partita = partita;
	}

	@Override
	public void setParametro(String parametro) {
		this.attrezzo = parametro;
		
	}

	@Override
	public void esegui(Partita partita) {
		Attrezzo attrezzoDaPrendere = this.partita.getLabirinto().getStanzaCorrente().getAttrezzo(this.attrezzo);
        if (attrezzoDaPrendere != null) {
            if (this.partita.getGiocatore().getBorsa().addAttrezzo(attrezzoDaPrendere)) {
                this.partita.getLabirinto().getStanzaCorrente().removeAttrezzo(attrezzoDaPrendere);
                IO.mostraMessaggio("Hai preso: " + attrezzoDaPrendere.getNome());
            } else {
            	IO.mostraMessaggio("La borsa è piena!");
            }
        } else {
        	IO.mostraMessaggio("Attrezzo non trovato.");
        }
		
	}
}
