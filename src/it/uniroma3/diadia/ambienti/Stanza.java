package it.uniroma3.diadia.ambienti;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import it.uniroma3.diadia.ConfigReader;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

/**
 * Classe Stanza - una stanza in un gioco di ruolo.
 * Una stanza è un luogo fisico nel gioco.
 * È collegata ad altre stanze attraverso delle uscite.
 * Ogni uscita è associata ad una direzione.
 * 
 * @author docente di POO 
 * @see Attrezzo
 * @version base
 */
public class Stanza {
    
    static final private int NUMERO_MASSIMO_DIREZIONI = ConfigReader.leggiValore("NUMERO_MASSIMO_DIREZIONI");
	private static final int NUMERO_MASSIMO_ATTREZZI = ConfigReader.leggiValore("NUMERO_MASSIMO_ATTREZZI");
    private String nome;
    
    private Map<String,Attrezzo> attrezzi;
    private int numeroAttrezzi;
    
    protected Map<String,Stanza> stanzeAdiacenti;
    private int numeroStanzeAdiacenti;
    private AbstractPersonaggio personaggio;

	
    
    /**
     * Crea una stanza. Non ci sono stanze adiacenti, non ci sono attrezzi.
     * @param nome il nome della stanza
     */
    public Stanza(String nome) {
        this.nome = nome;
        this.numeroStanzeAdiacenti = 0;
        this.numeroAttrezzi = 0;
        this.stanzeAdiacenti = new HashMap<String,Stanza>();
        this.attrezzi = new HashMap<String,Attrezzo>();
    }

    /** 
     * Imposta una stanza adiacente.
     * @param direzione direzione in cui sarà posta la stanza adiacente.
     * @param stanza stanza adiacente nella direzione indicata.
     */ 
    public void impostaStanzaAdiacente(String direzione, Stanza stanza) {
        this.stanzeAdiacenti.put(direzione, stanza);
        this.numeroStanzeAdiacenti++;
    }
 
    /**
     * Restituisce la stanza adiacente nella direzione specificata.
     * @param direzione la direzione.
     * @return la stanza adiacente, o null se non esiste.
     */
    public Stanza getStanzaAdiacente(String direzione) {
    	return this.stanzeAdiacenti.get(direzione);
    }

    /**
     * Restituisce il nome della stanza.
     * @return il nome.
     */
    public String getNome() {
        return this.nome;
    }
    
    public void setPersonaggio(AbstractPersonaggio personaggio) {
		this.personaggio = personaggio;
	}
	
	public AbstractPersonaggio getPersonaggio() {
		return this.personaggio;
	}

    /**
     * Restituisce la descrizione della stanza.
     * @return la descrizione.
     */
    public String getDescrizione() {
        return this.toString();
    }

    /**
     * Restituisce la collezione di attrezzi presenti nella stanza.
     * @return l'array di attrezzi.
     */
    public Map getAttrezzi() {
        return this.attrezzi;
    }

    /**
     * Mette un attrezzo nella stanza.
     * Riempie sempre il primo slot libero.
     * @param attrezzo l'attrezzo da inserire.
     * @return true se aggiunto, false se pieno.
     */
    public boolean addAttrezzo(Attrezzo attrezzo) {
    	if(this.attrezzi.size() >= NUMERO_MASSIMO_ATTREZZI) {
    	this.attrezzi.put(attrezzo.getNome(),attrezzo);
    	return this.attrezzi.get(attrezzo.getNome()) != null;}
    	else
    		return false;
    }

    /**
     * Rimuove un attrezzo dalla stanza (ricerca in base al nome).
     * Compatta l'array dopo la rimozione.
     * @param nomeAttrezzo il nome dell'attrezzo.
     * @return true se rimosso, false altrimenti.
     */
    public boolean removeAttrezzo(Attrezzo attrezzo) {
    	return this.attrezzi.remove(attrezzo.getNome()) != null;

    }

    /**
     * Controlla se un attrezzo esiste nella stanza (per nome).
     * @param nomeAttrezzo il nome da cercare.
     * @return true se presente, false altrimenti.
     */
    public boolean hasAttrezzo(String nomeAttrezzo) {
    	return this.attrezzi.get(nomeAttrezzo) != null;
    }

    /**
     * Restituisce l'attrezzo se presente (per nome).
     * @param nomeAttrezzo il nome da cercare.
     * @return l'attrezzo, o null.
     */
    public Attrezzo getAttrezzo(String nomeAttrezzo) {
    	return this.attrezzi.get(nomeAttrezzo);
    }

    /**
     * Restituisce le direzioni disponibili. 
     * @return array di stringhe.
     */
    public Collection<String> getDirezioni() {
       return this.stanzeAdiacenti.keySet();
    }

    /**
     * Rappresentazione stringa della stanza:
     * nome, uscite e attrezzi.
     */
    @Override
    public boolean equals(Object obj) {
    	if (this == obj)
    		return true;
    	if (obj == null)
    		return false;
    	if (getClass() != obj.getClass())
    		return false;
    	Stanza other = (Stanza) obj;
    	if (this.getNome() == null) {
    		return other.getNome() == null;
    	} else {
    		return this.getNome().equals(other.getNome());
    	}
    }

    @Override
    public int hashCode() {
    	return this.getNome() == null ? 0 : this.getNome().hashCode();
    }


    public int getNumeroAttrezzi() {
        return this.numeroAttrezzi;
    }

    // se non strettamente necessario, sconsiglio di esporre questo setter
    private void setNumeroAttrezzi(int numeroAttrezzi) {
        this.numeroAttrezzi = numeroAttrezzi;
    }

	public Map getMapStanzeAdiacenti() {
		return this.stanzeAdiacenti;
	}
	
	/**
     * Rappresentazione stringa della stanza:
     * nome, uscite e attrezzi.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.nome);
        sb.append("\nUscite: ");
        for (String dir : this.getDirezioni()) {
            if (dir != null)
                sb.append(" ").append(dir);
        }
        sb.append("\nAttrezzi nella stanza: ");
        for (Attrezzo a : this.attrezzi.values()) {
            if (a != null)
                sb.append(a.toString()).append(" ");
        }
        return sb.toString();
    }
}
