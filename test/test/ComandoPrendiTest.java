package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.*;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.ambienti.*;

class ComandoPrendiTest {

    private ComandoPrendi comando;
    private Partita partita;
    private ByteArrayOutputStream outputStream;
    private Stanza stanza;

    @BeforeEach
    void setUp() {
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        stanza = new Stanza("Biblioteca");
        Labirinto labirinto = new Labirinto();
        labirinto.setStanzaCorrente(stanza);

        partita = new Partita();
        partita.setLabirinto(labirinto);

        IOConsole io = new IOConsole();
        comando = new ComandoPrendi(io, partita);
    }

    @Test
    void testAttrezzoPresenteNellaStanza() {
        Attrezzo lanterna = new Attrezzo("lanterna", 2);
        stanza.addAttrezzo(lanterna);

        comando.setParametro("lanterna");
        comando.esegui(partita);

        String output = outputStream.toString();
        assertTrue(output.contains("Hai preso: lanterna"));
        assertTrue(partita.getGiocatore().getBorsa().hasAttrezzo("lanterna"));
        assertFalse(stanza.hasAttrezzo("lanterna"));
    }

    @Test
    void testAttrezzoNonPresenteNellaStanza() {
        comando.setParametro("spada");
        comando.esegui(partita);

        String output = outputStream.toString();
        assertTrue(output.contains("Attrezzo non trovato."));
        assertFalse(partita.getGiocatore().getBorsa().hasAttrezzo("spada"));
    }

    @Test
    void testBorsaPiena() {
        // Riempie la borsa manualmente
        for (int i = 0; i < 10; i++) {
            partita.getGiocatore().getBorsa().addAttrezzo(new Attrezzo("oggetto" + i, 1));
        }

        Attrezzo pesante = new Attrezzo("incudine", 5);
        stanza.addAttrezzo(pesante);

        comando.setParametro("incudine");
        comando.esegui(partita);

        String output = outputStream.toString();
        assertTrue(output.contains("La borsa è piena!") || output.contains("Attrezzo non trovato."));
    }
}
