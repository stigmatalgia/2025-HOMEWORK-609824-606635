package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPrendi extends AbstractComando implements Comando {
	private String attrezzo; 
	
	public ComandoPrendi() {
	}

	@Override
	public void setParametro(String parametro) {
		this.attrezzo = parametro;
		
	} 

	@Override  
	public void esegui(Partita partita, IO IO) {
		Attrezzo attrezzoDaPrendere = partita.getStanzaCorrente().getAttrezzo(this.attrezzo);
        if (attrezzoDaPrendere != null) {
            if (partita.getGiocatore().getBorsa().addAttrezzo(attrezzoDaPrendere)) {
                partita.getStanzaCorrente().removeAttrezzo(attrezzoDaPrendere);
                IO.mostraMessaggio("Hai preso: " + attrezzoDaPrendere.getNome());
            } else {
            	IO.mostraMessaggio("La borsa è piena!");
            }
        } else {
        	IO.mostraMessaggio("Attrezzo non trovato.");
        }
		
	}

	@Override
	public String getNome() {
		return "prendi";
	}

	@Override
	public String getParametro() {
		return this.attrezzo;
	}
}
