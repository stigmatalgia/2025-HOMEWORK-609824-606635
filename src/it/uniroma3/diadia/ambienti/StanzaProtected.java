package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.ConfigReader;
import it.uniroma3.diadia.attrezzi.Attrezzo;

import java.util.ArrayList;
import java.util.List;

public class StanzaProtected {

    private static final int NUMERO_MASSIMO_DIREZIONI = ConfigReader.leggiValore("NUMERO_MASSIMO_DIREZIONI");
    private static final int NUMERO_MASSIMO_ATTREZZI = ConfigReader.leggiValore("NUMERO_MASSIMO_ATTREZZI");

    protected String nome;

    protected List<Attrezzo> attrezzi;
    protected List<Stanza> stanzeAdiacenti;
    protected List<Direzione> direzioni;

    public StanzaProtected(String nome) {
        this.nome = nome;
        this.attrezzi = new ArrayList<>();
        this.stanzeAdiacenti = new ArrayList<>();
        this.direzioni = new ArrayList<>();
    }

    public void impostaStanzaAdiacente(Direzione direzione, Stanza stanza) {
        int index = direzioni.indexOf(direzione);
        if (index != -1) {
            stanzeAdiacenti.set(index, stanza);
        } else {
            if (stanzeAdiacenti.size() < NUMERO_MASSIMO_DIREZIONI) {
                direzioni.add(direzione);
                stanzeAdiacenti.add(stanza);
            }
        }
    }

    public Stanza getStanzaAdiacente(String direzione) {
        for (int i = 0; i < direzioni.size(); i++) {
            if (direzioni.get(i).equals(direzione)) {
                return stanzeAdiacenti.get(i);
            }
        }
        return null;
    }

    public String getNome() {
        return this.nome;
    }

    public String getDescrizione() {
        return this.toString();
    }

    public List<Attrezzo> getAttrezzi() {
        return this.attrezzi;
    }

    public boolean addAttrezzo(Attrezzo attrezzo) {
        if (attrezzi.size() < NUMERO_MASSIMO_ATTREZZI) {
            return attrezzi.add(attrezzo);
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder risultato = new StringBuilder();
        risultato.append(this.nome);
        risultato.append("\nUscite: ");
        for (Direzione direzione : direzioni) {
            risultato.append(" ").append(direzione);
        }
        risultato.append("\nAttrezzi nella stanza: ");
        for (Attrezzo attrezzo : attrezzi) {
            risultato.append(attrezzo.toString()).append(" ");
        }
        return risultato.toString();
    }

    public boolean hasAttrezzo(String nomeAttrezzo) {
        for (Attrezzo attrezzo : attrezzi) {
            if (attrezzo.getNome().equals(nomeAttrezzo)) {
                return true;
            }
        }
        return false;
    }

    public Attrezzo getAttrezzo(String nomeAttrezzo) {
        for (Attrezzo attrezzo : attrezzi) {
            if (attrezzo.getNome().equals(nomeAttrezzo)) {
                return attrezzo;
            }
        }
        return null;
    }

    public boolean removeAttrezzo(Attrezzo attrezzo) {
        return attrezzi.remove(attrezzo);
    }

    public List<Direzione> getDirezioni() {
        return new ArrayList<>(direzioni);
    }
}
