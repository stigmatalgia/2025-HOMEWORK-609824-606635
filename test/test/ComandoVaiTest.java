package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.*;
import it.uniroma3.diadia.ambienti.*;

class ComandoVaiTest {

    private Partita partita;
    private Labirinto labirinto;
    private ComandoVai comando;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    void setUp() {
        // Cattura System.out
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Crea stanze collegate
        Stanza stanzaIniziale = new Stanza("Ingresso");
        Stanza stanzaNord = new Stanza("Biblioteca");
        stanzaIniziale.impostaStanzaAdiacente("nord", stanzaNord);

        // Labirinto
        labirinto = new Labirinto();
        labirinto.setStanzaCorrente(stanzaIniziale);
        labirinto.setStanzaVincente(stanzaNord);

        // Partita
        partita = new Partita();
        partita.setLabirinto(labirinto);
        partita.getGiocatore().setCfu(10);

        // IO normale e comando
        IOConsole ioConsole = new IOConsole();
        comando = new ComandoVai(ioConsole, partita);
    }

    @Test
    void testDirezioneNull() {
        comando.setParametro(null);
        comando.esegui(partita);

        String output = outputStream.toString();
        assertTrue(output.contains("Dove vuoi andare ?"));
    }

    @Test
    void testDirezioneInesistente() {
        comando.setParametro("sud"); // non esiste
        comando.esegui(partita);

        String output = outputStream.toString();
        assertTrue(output.contains("Direzione inesistente"));
        assertEquals("Ingresso", partita.getLabirinto().getStanzaCorrente().getNome());
    }

    @Test
    void testDirezioneValida() {
        comando.setParametro("nord");
        comando.esegui(partita);

        String output = outputStream.toString();
        assertTrue(output.contains("Biblioteca"));
        assertEquals("Biblioteca", partita.getLabirinto().getStanzaCorrente().getNome());
        assertEquals(9, partita.getGiocatore().getCfu());
    }

    @Test
    void testFinePartitaPerCfuZero() {
        partita.getGiocatore().setCfu(1); // scenderà a 0
        comando.setParametro("nord");
        comando.esegui(partita);

        assertTrue(partita.isFinita());
    }
}
