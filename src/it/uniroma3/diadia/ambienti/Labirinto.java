package it.uniroma3.diadia.ambienti;
import java.util.HashMap;
import java.util.Map;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Labirinto {
	private Stanza stanzaVincente;
	private Stanza stanzaIniziale;
	private Map<String,Stanza> struttura = new HashMap<>();
	
	public Labirinto() {

	}
	
	/**
     * Crea tutte le stanze e le porte di collegamento
     */

	public Stanza getStanzaVincente() {
		return this.stanzaVincente;
	}

	public void setStanzaVincente(Stanza stanzaVincente) {
		this.stanzaVincente = stanzaVincente;
		
	}

	public Stanza getStanzaIniziale() {
		return stanzaIniziale;
	}

	public void setStanzaIniziale(Stanza stanzaIniziale) {
		this.stanzaIniziale = stanzaIniziale;
	}

	public Map<String,Stanza> getStruttura() {
		return struttura;
	}

	public void setStruttura(Map<String,Stanza> struttura) {
		this.struttura = struttura;
	}
}
