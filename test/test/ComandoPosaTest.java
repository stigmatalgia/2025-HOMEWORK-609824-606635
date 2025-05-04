package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.*;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.ambienti.*;

class ComandoPosaTest {

    private ComandoPosa comando;
    private Partita partita;
    private ByteArrayOutputStream outputStream;
    private Stanza stanza;

    @BeforeEach
    void setUp() {
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        stanza = new Stanza("Aula");
        Labirinto labirinto = new Labirinto();
        labirinto.setStanzaCorrente(stanza);

        partita = new Partita();
        partita.setLabirinto(labirinto);

        IOConsole io = new IOConsole();
        comando = new ComandoPosa(io, partita);
    }

    @Test
    void testAttrezzoPresenteNellaBorsa() {
        Attrezzo libro = new Attrezzo("libro", 1);
        partita.getGiocatore().getBorsa().addAttrezzo(libro);

        comando.setParametro("libro");
        comando.esegui(partita);

        String output = outputStream.toString();
        assertTrue(output.contains("Hai posato: libro"));
        assertTrue(stanza.hasAttrezzo("libro"));
    }

    @Test
    void testAttrezzoNonPresenteNellaBorsa() {
        comando.setParametro("chiave");
        comando.esegui(partita);

        String output = outputStream.toString();
        assertTrue(output.contains("Non hai questo attrezzo nella borsa."));
        assertFalse(stanza.hasAttrezzo("chiave"));
    }
}
