package it.uniroma3.diadia.ambienti;
import it.uniroma3.diadia.attrezzi.Attrezzo;

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
    
    static final private int NUMERO_MASSIMO_DIREZIONI = 4;
    static final private int NUMERO_MASSIMO_ATTREZZI = 10;
    
    private String nome;
    
    private Attrezzo[] attrezzi;
    private int numeroAttrezzi;
    
    private Stanza[] stanzeAdiacenti;
    private int numeroStanzeAdiacenti;
    
    private String[] direzioni;
    
    /**
     * Crea una stanza. Non ci sono stanze adiacenti, non ci sono attrezzi.
     * @param nome il nome della stanza
     */
    public Stanza(String nome) {
        this.nome = nome;
        this.numeroStanzeAdiacenti = 0;
        this.numeroAttrezzi = 0;
        this.direzioni = new String[NUMERO_MASSIMO_DIREZIONI];
        this.stanzeAdiacenti = new Stanza[NUMERO_MASSIMO_DIREZIONI];
        this.attrezzi = new Attrezzo[NUMERO_MASSIMO_ATTREZZI];
    }

    /** 
     * Imposta una stanza adiacente.
     * @param direzione direzione in cui sarà posta la stanza adiacente.
     * @param stanza stanza adiacente nella direzione indicata.
     */ 
    public void impostaStanzaAdiacente(String direzione, Stanza stanza) {
        boolean aggiornato = false;
        for (int i = 0; i < this.direzioni.length; i++) {
            if (direzione.equals(this.direzioni[i])) {
                this.stanzeAdiacenti[i] = stanza;
                aggiornato = true;
                break;
            }
        }
        if (!aggiornato && this.numeroStanzeAdiacenti < NUMERO_MASSIMO_DIREZIONI) {
            this.direzioni[numeroStanzeAdiacenti] = direzione;
            this.stanzeAdiacenti[numeroStanzeAdiacenti] = stanza;
            this.numeroStanzeAdiacenti++;
        }
    }
 
    /**
     * Restituisce la stanza adiacente nella direzione specificata.
     * @param direzione la direzione.
     * @return la stanza adiacente, o null se non esiste.
     */
    public Stanza getStanzaAdiacente(String direzione) {
        for (int i = 0; i < this.numeroStanzeAdiacenti; i++) {
            if (this.direzioni[i].equals(direzione))
                return this.stanzeAdiacenti[i]; 
        }
        return null;
    }

    /**
     * Restituisce il nome della stanza.
     * @return il nome.
     */
    public String getNome() {
        return this.nome;
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
    public Attrezzo[] getAttrezzi() {
        return this.attrezzi;
    }

    /**
     * Mette un attrezzo nella stanza.
     * Riempie sempre il primo slot libero.
     * @param attrezzo l'attrezzo da inserire.
     * @return true se aggiunto, false se pieno.
     */
    public boolean addAttrezzo(Attrezzo attrezzo) {
        if (this.numeroAttrezzi >= NUMERO_MASSIMO_ATTREZZI)
            return false;
        for (int i = 0; i < this.attrezzi.length; i++) {
            if (this.attrezzi[i] == null) {
                this.attrezzi[i] = attrezzo;
                this.numeroAttrezzi++;
                return true;
            }
        }
        return false; // non dovrebbe mai accadere
    }

    /**
     * Rimuove un attrezzo dalla stanza (ricerca in base al nome).
     * Compatta l'array dopo la rimozione.
     * @param nomeAttrezzo il nome dell'attrezzo.
     * @return true se rimosso, false altrimenti.
     */
    public boolean removeAttrezzo(Attrezzo attrezzo) {
    	String nomeAttrezzo = attrezzo.getNome();
        for (int i = 0; i < this.numeroAttrezzi; i++) {
            Attrezzo a = this.attrezzi[i];
            if (a != null && a.getNome().equals(nomeAttrezzo)) {
                for (int j = i; j < this.numeroAttrezzi - 1; j++) {
                    this.attrezzi[j] = this.attrezzi[j + 1];
                }
                this.attrezzi[this.numeroAttrezzi - 1] = null;
                this.numeroAttrezzi--;
                return true;
            }
        }
        return false;
    }

    /**
     * Controlla se un attrezzo esiste nella stanza (per nome).
     * @param nomeAttrezzo il nome da cercare.
     * @return true se presente, false altrimenti.
     */
    public boolean hasAttrezzo(String nomeAttrezzo) {
        for (Attrezzo a : this.attrezzi) {
            if (a != null && a.getNome().equals(nomeAttrezzo))
                return true;
        }
        return false;
    }

    /**
     * Restituisce l'attrezzo se presente (per nome).
     * @param nomeAttrezzo il nome da cercare.
     * @return l'attrezzo, o null.
     */
    public Attrezzo getAttrezzo(String nomeAttrezzo) {
        for (Attrezzo a : this.attrezzi) {
            if (a != null && a.getNome().equals(nomeAttrezzo))
                return a;
        }
        return null;
    }

    /**
     * Restituisce le direzioni disponibili. 
     * @return array di stringhe.
     */
    public String[] getDirezioni() {
        String[] risultato = new String[this.numeroStanzeAdiacenti];
        for (int i = 0; i < this.numeroStanzeAdiacenti; i++)
            risultato[i] = this.direzioni[i];
        return risultato;
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
        for (String dir : this.direzioni) {
            if (dir != null)
                sb.append(" ").append(dir);
        }
        sb.append("\nAttrezzi nella stanza: ");
        for (Attrezzo a : this.attrezzi) {
            if (a != null)
                sb.append(a.toString()).append(" ");
        }
        return sb.toString();
    }

    public int getNumeroAttrezzi() {
        return this.numeroAttrezzi;
    }

    // se non strettamente necessario, sconsiglio di esporre questo setter
    public void setNumeroAttrezzi(int numeroAttrezzi) {
        this.numeroAttrezzi = numeroAttrezzi;
    }
}
