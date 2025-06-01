package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
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

        Labirinto labuilder = new LabirintoBuilder()
				.addStanzaIniziale("LabCampusOne")
				.addStanzaVincente("Biblioteca")
				.addAdiacenza("LabCampusOne", "Biblioteca", "ovest")
				.getLabirinto(); 
        stanza = labuilder.getStanzaIniziale();
		partita = new Partita(labuilder);

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
