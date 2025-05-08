package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.ComandoPosa;

class ComandoPosaTest {

    private Partita partita;
    private ComandoPosa comando;
    private IO io;
    private Stanza stanza;

    @BeforeEach
    void setUp() {
        stanza = new Stanza("Aula");
        Labirinto labirinto = new Labirinto();
        labirinto.setStanzaIniziale(stanza);
        labirinto.setStanzaCorrente(stanza);

        partita = new Partita();
        partita.setLabirinto(labirinto);

        io = new IOConsole();
        comando = new ComandoPosa();
    }

    @Test
    void testPosaSuccesso() {
        Attrezzo libro = new Attrezzo("libro", 1);
        partita.getGiocatore().getBorsa().addAttrezzo(libro);

        comando.setParametro("libro");
        comando.esegui(partita,io);
        
        assertFalse(partita.getGiocatore().getBorsa().hasAttrezzo("libro"));
        assertTrue(stanza.hasAttrezzo("libro"));
    }

    @Test
    void testPosaFallimento() {
        comando.setParametro("chiave");
        comando.esegui(partita,io);

        assertFalse(stanza.hasAttrezzo("chiave"));
    }
}
