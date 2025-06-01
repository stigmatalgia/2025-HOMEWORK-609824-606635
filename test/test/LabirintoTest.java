package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.ambienti.Stanza;

public class LabirintoTest {

    private Labirinto labirinto;

    @BeforeEach
    public void setUp() {
        labirinto = new LabirintoBuilder()
                .addStanzaIniziale("Atrio")
                .addAttrezzo("osso", 1)
                .addStanzaVincente("Biblioteca")
                .addStanza("Aula N11")
                .addStanza("Aula N10")
                .addAttrezzo("lanterna", 3)
                .addStanza("Laboratorio Campus")
                .addAdiacenza("Atrio", "Biblioteca", "nord")
                .addAdiacenza("Atrio", "Aula N11", "est")
                .addAdiacenza("Atrio", "Aula N10", "sud")
                .addAdiacenza("Atrio", "Laboratorio Campus", "ovest")
                .getLabirinto();
    }

    @Test
    public void testStanzeIniziali() {
        Stanza atrio = labirinto.getStanzaCorrente();
        assertNotNull(atrio);
        assertEquals("Atrio", atrio.getNome());

        Stanza vincente = labirinto.getStanzaVincente();
        assertNotNull(vincente);
        assertEquals("Biblioteca", vincente.getNome());
    }

    @Test
    public void testConnessioniStanze() {
        Stanza atrio = labirinto.getStanzaCorrente();
        assertNotNull(atrio.getStanzaAdiacente("nord"));
        assertEquals("Biblioteca", atrio.getStanzaAdiacente("nord").getNome());

        assertNotNull(atrio.getStanzaAdiacente("est"));
        assertEquals("Aula N11", atrio.getStanzaAdiacente("est").getNome());

        assertNotNull(atrio.getStanzaAdiacente("sud"));
        assertEquals("Aula N10", atrio.getStanzaAdiacente("sud").getNome());

        assertNotNull(atrio.getStanzaAdiacente("ovest"));
        assertEquals("Laboratorio Campus", atrio.getStanzaAdiacente("ovest").getNome());
    }

    @Test
    public void testAttrezziNelleStanze() {
        Stanza atrio = labirinto.getStanzaCorrente();
        assertTrue(atrio.hasAttrezzo("osso"));

        Stanza aulaN10 = atrio.getStanzaAdiacente("sud");
        assertNotNull(aulaN10);
        assertTrue(aulaN10.hasAttrezzo("lanterna"));
    }
}
