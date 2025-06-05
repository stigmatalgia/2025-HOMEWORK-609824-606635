package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;

public class LabirintoTest {

    private Labirinto labirinto;
    private Partita partita;

    @BeforeEach
    public void setUp() {
        labirinto = Labirinto.newBuilder()
                .addStanzaIniziale("Atrio")
                .addAttrezzo("osso", 1)
                .addStanzaVincente("Biblioteca")
                .addStanza("Aula N11")
                .addStanza("Aula N10")
                .addAttrezzo("lanterna", 3)
                .addStanza("Laboratorio Campus")
                .addAdiacenza("Atrio", "Biblioteca", Direzione.NORD)
                .addAdiacenza("Atrio", "Aula N11", Direzione.EST)
                .addAdiacenza("Atrio", "Aula N10", Direzione.SUD)
                .addAdiacenza("Atrio", "Laboratorio Campus", Direzione.OVEST)
                .getLabirinto();
        this.partita = new Partita(this.labirinto);
    }

    @Test
    public void testStanzeIniziali() {
        Stanza atrio = partita.getStanzaCorrente();
        assertNotNull(atrio);
        assertEquals("Atrio", atrio.getNome());

        Stanza vincente = labirinto.getStanzaVincente();
        assertNotNull(vincente);
        assertEquals("Biblioteca", vincente.getNome());
    }

    @Test
    public void testConnessioniStanze() {
        Stanza atrio = partita.getStanzaCorrente();
        assertNotNull(atrio.getStanzaAdiacente(Direzione.OVEST));
        assertEquals("Biblioteca", atrio.getStanzaAdiacente(Direzione.NORD).getNome());

        assertNotNull(atrio.getStanzaAdiacente(Direzione.EST));
        assertEquals("Aula N11", atrio.getStanzaAdiacente(Direzione.EST).getNome());

        assertNotNull(atrio.getStanzaAdiacente(Direzione.SUD));
        assertEquals("Aula N10", atrio.getStanzaAdiacente(Direzione.SUD).getNome());

        assertNotNull(atrio.getStanzaAdiacente(Direzione.OVEST));
        assertEquals("Laboratorio Campus", atrio.getStanzaAdiacente(Direzione.OVEST).getNome());
    }

    @Test
    public void testAttrezziNelleStanze() {
        Stanza atrio = partita.getStanzaCorrente();
        assertTrue(atrio.hasAttrezzo("osso"));

        Stanza aulaN10 = atrio.getStanzaAdiacente(Direzione.SUD);
        assertNotNull(aulaN10);
        assertTrue(aulaN10.hasAttrezzo("lanterna"));
    }
}
