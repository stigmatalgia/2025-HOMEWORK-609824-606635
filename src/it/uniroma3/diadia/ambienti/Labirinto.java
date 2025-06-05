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
	
	public static LabirintoBuilder newBuilder() {
        return new LabirintoBuilder();
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

	public static class LabirintoBuilder {
		private Labirinto labirinto;
		private Stanza ultimaAggiunta;

		public LabirintoBuilder() {
			this.labirinto = new Labirinto();
		}

		public LabirintoBuilder addStanzaIniziale(String nomeStanza) {
			Stanza s;
			if (!this.labirinto.getStruttura().containsKey(nomeStanza)) {
				s = new Stanza(nomeStanza);
				this.labirinto.getStruttura().put(nomeStanza, s);
			} else {
				s = this.labirinto.getStruttura().get(nomeStanza);
			}
			this.labirinto.setStanzaIniziale(s);
			this.ultimaAggiunta = s;
			return this;
		}

		public LabirintoBuilder addStanza(String nomeStanza) {
			if (!this.labirinto.getStruttura().containsKey(nomeStanza)) {
				Stanza s = new Stanza(nomeStanza);
				this.labirinto.getStruttura().put(nomeStanza, s);
				this.ultimaAggiunta = s;
			} else {
				this.ultimaAggiunta = this.labirinto.getStruttura().get(nomeStanza);
			}
			return this;
		}

		public LabirintoBuilder addStanzaVincente(String nomeStanza) {
			Stanza s;
			if (!this.labirinto.getStruttura().containsKey(nomeStanza)) {
				s = new Stanza(nomeStanza);
				this.labirinto.getStruttura().put(nomeStanza, s);
			} else {
				s = this.labirinto.getStruttura().get(nomeStanza);
			}
			this.labirinto.setStanzaVincente(s);
			this.ultimaAggiunta = s;
			return this;
		}

		public LabirintoBuilder addStanzaBloccata(String nomeStanza, Direzione direzioneBloccata, String oggettoSblocca) {
			Stanza s = new StanzaBloccata(nomeStanza, direzioneBloccata, oggettoSblocca);
			this.labirinto.getStruttura().put(nomeStanza, s);
			this.ultimaAggiunta = s;
			return this;
		}

		public LabirintoBuilder addStanzaMagica(String nomeStanza, int soglia) {
			Stanza s = new StanzaMagica(nomeStanza, soglia);
			this.labirinto.getStruttura().put(nomeStanza, s);
			this.ultimaAggiunta = s;
			return this;
		}

		public LabirintoBuilder addStanzaBuia(String nomeStanza, String oggettoSblocca) {
			Stanza s = new StanzaBuia(nomeStanza, oggettoSblocca);
			this.labirinto.getStruttura().put(nomeStanza, s);
			this.ultimaAggiunta = s;
			return this;
		}

		public LabirintoBuilder addAttrezzo(String nome, int peso) {
			if (this.ultimaAggiunta != null) {
				Attrezzo a = new Attrezzo(nome, peso);
				this.ultimaAggiunta.addAttrezzo(a);
			}
			return this;
		}

		private Stanza addStanzaReturn(String nomeStanza) {
			Stanza s = new Stanza(nomeStanza);
			this.ultimaAggiunta = s;
			this.labirinto.getStruttura().put(nomeStanza, s);
			return s;
		}

		public LabirintoBuilder addAdiacenza(String nomeStanza1, String nomeStanza2, Direzione direzione) {
			Stanza s1;
			Stanza s2;
			if (this.labirinto.getStruttura().containsKey(nomeStanza1)) {
				s1 = this.labirinto.getStruttura().get(nomeStanza1);
			} else {
				s1 = addStanzaReturn(nomeStanza1);
			}
			if (this.labirinto.getStruttura().containsKey(nomeStanza2)) {
				s2 = this.labirinto.getStruttura().get(nomeStanza2);
			} else {
				s2 = addStanzaReturn(nomeStanza2);
			}
			// aggiunge l'adiacenza solo se ci sono meno di 4 direzioni già impostate
			if (s1.getMapStanzeAdiacenti().size() < 4) {
				s1.impostaStanzaAdiacente(direzione, s2);
			}
			return this;
		}
		
		public Labirinto getLabirinto() {
			return this.labirinto;
		}
		
		public Map<String, Stanza> getListaStanze() {
			return this.labirinto.getStruttura();
		}
	}
}
