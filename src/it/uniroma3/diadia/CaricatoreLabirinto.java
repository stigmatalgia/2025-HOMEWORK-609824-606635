package it.uniroma3.diadia;

import java.io.*;
import java.util.*;

import it.uniroma3.diadia.ambienti.*;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.Cane;
import it.uniroma3.diadia.personaggi.Mago;
import it.uniroma3.diadia.personaggi.Strega;

public class CaricatoreLabirinto {

    private static final String STANZE_MARKER          = "Stanze:";
    private static final String STANZE_BUIE_MARKER     = "Stanze buie:";
    private static final String STANZE_MAGICHE_MARKER  = "Stanze magiche:";
    private static final String STANZE_BLOCCATE_MARKER = "Stanze bloccate:";
    private static final String STANZA_INIZIALE_MARKER = "Inizio:";
    private static final String STANZA_VINCENTE_MARKER = "Vincente:";
    private static final String ATTREZZI_MARKER        = "Attrezzi:";
    private static final String USCITE_MARKER          = "Uscite:";
    private static final String PERSONAGGI_MARKER      = "Personaggi:";

    private LineNumberReader reader;
    private Map<String, Stanza> nome2stanza;
    private Stanza stanzaIniziale;
    private Stanza stanzaVincente;
    private LabirintoBuilder builder;

    public CaricatoreLabirinto(Reader reader) {
        this.nome2stanza = new HashMap<>();
        this.reader       = new LineNumberReader(reader);
        this.builder      = new LabirintoBuilder();
    }

    public void carica() throws FormatoFileNonValidoException {
        try {
            this.leggiECreaStanze();
            this.leggiInizialeEvincente();
            this.leggiECollocaAttrezzi();
            this.leggiEImpostaUscite();
            this.leggiECreaPersonaggi();
        } finally {
            try {
                this.reader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private String leggiRigaCheCominciaPer(String marker) throws FormatoFileNonValidoException {
        try {
            String riga = this.reader.readLine();
            check(riga != null && riga.startsWith(marker), "era attesa una riga che cominciasse per " + marker);
            return riga.substring(marker.length()).trim();
        } catch (IOException e) {
            throw new FormatoFileNonValidoException(e.getMessage());
        }
    }

    private void leggiECreaStanze() throws FormatoFileNonValidoException {
        String nomiStanze = this.leggiRigaCheCominciaPer(STANZE_MARKER);
        for (String nomeStanza : separaStringheAlleVirgole(nomiStanze)) {
            nomeStanza = nomeStanza.trim();
            builder.addStanza(nomeStanza);
        }

        String righeBuie = this.leggiRigaCheCominciaPer(STANZE_BUIE_MARKER);
        if (!righeBuie.isEmpty()) {
            for (String spec : separaStringheAlleVirgole(righeBuie)) {
                String[] tokens = spec.trim().split("\\s+");
                check(tokens.length == 2, "Formato stanza buia errato. Atteso: <nomeStanza> <oggettoLuce>");
                builder.addStanzaBuia(tokens[0], tokens[1]);
            }
        }

        String righeMagiche = this.leggiRigaCheCominciaPer(STANZE_MAGICHE_MARKER);
        if (!righeMagiche.isEmpty()) {
            for (String spec : separaStringheAlleVirgole(righeMagiche)) {
                String[] tokens = spec.trim().split("\\s+");
                check(tokens.length == 2, "Formato stanza magica errato. Atteso: <nomeStanza> <soglia>");
                String nomeStanza = tokens[0];
                int soglia;
                try {
                    soglia = Integer.parseInt(tokens[1]);
                } catch (NumberFormatException e) {
                    throw new FormatoFileNonValidoException("Soglia magica non valida: " + tokens[1]);
                }
                builder.addStanzaMagica(nomeStanza, soglia);
            }
        }

        String righeBloccate = this.leggiRigaCheCominciaPer(STANZE_BLOCCATE_MARKER);
        if (!righeBloccate.isEmpty()) {
            for (String spec : separaStringheAlleVirgole(righeBloccate)) {
                String[] tokens = spec.trim().split("\\s+");
                check(tokens.length == 3, "Formato stanza bloccata errato. Atteso: <nomeStanza> <direzioneBloccata> <oggettoChiave>");
                builder.addStanzaBloccata(tokens[0], tokens[1], tokens[2]);
            }
        }

        this.nome2stanza = this.builder.getListaStanze();
    }

    private void leggiInizialeEvincente() throws FormatoFileNonValidoException {
        String nomeIniziale = this.leggiRigaCheCominciaPer(STANZA_INIZIALE_MARKER);
        check(this.builder.getListaStanze().containsKey(nomeIniziale), nomeIniziale + " non definita");
        builder.addStanzaIniziale(nomeIniziale);
        this.stanzaIniziale = builder.getLabirinto().getStanzaIniziale();

        String nomeVincente = this.leggiRigaCheCominciaPer(STANZA_VINCENTE_MARKER);
        check(this.builder.getListaStanze().containsKey(nomeVincente), nomeVincente + " non definita");
        builder.addStanzaVincente(nomeVincente);
        this.stanzaVincente = builder.getLabirinto().getStanzaVincente();

        this.nome2stanza = this.builder.getListaStanze();
    }

    private void leggiECollocaAttrezzi() throws FormatoFileNonValidoException {
        String righeAttrezzi = this.leggiRigaCheCominciaPer(ATTREZZI_MARKER);
        if (righeAttrezzi.isEmpty()) return;

        for (String spec : separaStringheAlleVirgole(righeAttrezzi)) {
            String[] tokens = spec.trim().split("\\s+");
            check(tokens.length == 3, "Formato attrezzo errato. Atteso: <nomeAttrezzo> <peso> <stanza>");
            String nome     = tokens[0];
            int peso;
            try {
                peso = Integer.parseInt(tokens[1]);
            } catch (NumberFormatException e) {
                throw new FormatoFileNonValidoException("Peso attrezzo non valido: " + tokens[1]);
            }
            String stanzaDest = tokens[2];
            check(isStanzaValida(stanzaDest), "Attrezzo " + nome + " non collocabile: stanza " + stanzaDest + " inesistente");
            Attrezzo a = new Attrezzo(nome, peso);
            this.nome2stanza.get(stanzaDest).addAttrezzo(a);
        }
    }

    private void leggiEImpostaUscite() throws FormatoFileNonValidoException {
        String righeUscite = this.leggiRigaCheCominciaPer(USCITE_MARKER);
        if (righeUscite.isEmpty()) return;

        for (String spec : separaStringheAlleVirgole(righeUscite)) {
            try (Scanner sc = new Scanner(spec.trim())) {
                check(sc.hasNext(), msgTerminazionePrecoce("nome stanza di partenza"));
                String stanzaDa = sc.next().trim();
                check(sc.hasNext(), msgTerminazionePrecoce("direzione"));
                String direzione = sc.next().trim();
                check(sc.hasNext(), msgTerminazionePrecoce("nome stanza di destinazione"));
                String stanzaA = sc.next().trim();
                check(isStanzaValida(stanzaDa), "Stanza di partenza sconosciuta " + direzione);
                check(isStanzaValida(stanzaA),    "Stanza di destinazione sconosciuta " + direzione);
                builder.addAdiacenza(stanzaDa, stanzaA, direzione);
            }
        }
        this.nome2stanza = this.builder.getListaStanze();
    }

    private void leggiECreaPersonaggi() throws FormatoFileNonValidoException {
        String righePers = this.leggiRigaCheCominciaPer(PERSONAGGI_MARKER);
        if (righePers.isEmpty()) return;

        for (String spec : separaStringheAlleVirgole(righePers)) {
            String[] tokens = spec.trim().split("\\s+");
            check(tokens.length >= 4, "Formato personaggio errato: " + spec);
            String tipo          = tokens[0];
            String nomePers      = tokens[1];
            String presentazione = tokens[2];
            String stanzaDiPers  = tokens[3];
            check(isStanzaValida(stanzaDiPers), "Stanza " + stanzaDiPers + " per il personaggio " + nomePers + " non esistente");
            switch (tipo.toLowerCase()) {
                case "mago":
                    check(tokens.length == 6, "Formato mago errato. Atteso: Mago <nome> <presentazione> <stanza> <attrezzo> <peso>");
                    String nomeAttr = tokens[4];
                    int pesoAttr;
                    try {
                        pesoAttr = Integer.parseInt(tokens[5]);
                    } catch (NumberFormatException e) {
                        throw new FormatoFileNonValidoException("Peso attrezzo non valido per mago: " + tokens[5]);
                    }
                    Mago m = new Mago(nomePers, presentazione, new Attrezzo(nomeAttr, pesoAttr));
                    this.nome2stanza.get(stanzaDiPers).setPersonaggio(m);
                    break;
                case "strega":
                    check(tokens.length == 4, "Formato strega errato. Atteso: Strega <nome> <presentazione> <stanza>");
                    Strega s = new Strega(nomePers, presentazione);
                    this.nome2stanza.get(stanzaDiPers).setPersonaggio(s);
                    break;
                case "cane":
                    check(tokens.length == 5, "Formato cane errato. Atteso: Cane <nome> <presentazione> <stanza> <cibo>");
                    String cibo = tokens[4];
                    Cane c = new Cane(nomePers, presentazione, cibo);
                    this.nome2stanza.get(stanzaDiPers).setPersonaggio(c);
                    break;
                default:
                    throw new FormatoFileNonValidoException("Tipo personaggio sconosciuto: " + tipo);
            }
        }
    }

    private List<String> separaStringheAlleVirgole(String string) {
        List<String> result = new LinkedList<>();
        Scanner scanner = new Scanner(string);
        scanner.useDelimiter(",");
        while (scanner.hasNext()) {
            result.add(scanner.next().trim());
        }
        scanner.close();
        return result;
    }

    private boolean isStanzaValida(String nomeStanza) {
        return this.builder.getListaStanze().containsKey(nomeStanza);
    }

    private String msgTerminazionePrecoce(String msg) {
        return "Terminazione precoce del file prima di leggere " + msg;
    }

    private void check(boolean condizione, String messaggioErrore) throws FormatoFileNonValidoException {
        if (!condizione) {
            throw new FormatoFileNonValidoException("Formato file non valido [" + this.reader.getLineNumber() + "] " + messaggioErrore);
        }
    }

    public Stanza getStanzaIniziale() {
        return this.stanzaIniziale;
    }

    public Stanza getStanzaVincente() {
        return this.stanzaVincente;
    }

    public Map<String, Stanza> getListaStanze() {
        return this.builder.getListaStanze();
    }
}
