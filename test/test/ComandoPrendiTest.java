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
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.ComandoPrendi;

class ComandoPrendiTest {

    private Partita partita;
    private ComandoPrendi comando;
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

        comando = new ComandoPrendi();
        io = new IOConsole();
    }

    @Test
    void testPrendiSuccesso() {
        Attrezzo martello = new Attrezzo("martello", 2);
        stanza.addAttrezzo(martello);

        comando.setParametro("martello");
        comando.esegui(partita, io);

        assertTrue(partita.getGiocatore().getBorsa().hasAttrezzo("martello"));
        assertFalse(stanza.hasAttrezzo("martello"));
    }

    @Test
    void testPrendiFallimento() {

        comando.setParametro("chiave");
        comando.esegui(partita, io);

        assertFalse(partita.getGiocatore().getBorsa().hasAttrezzo("chiave"));
    }
}
