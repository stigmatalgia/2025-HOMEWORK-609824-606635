package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.uniroma3.diadia.giocatore.Giocatore;
import it.uniroma3.diadia.giocatore.Borsa;

public class GiocatoreTest {

    private Giocatore giocatore;

    @BeforeEach
    public void setUp() {
        giocatore = new Giocatore();
    }

    @Test
    public void testCfuIniziali() {
        assertEquals(20, giocatore.getCfu());
    }

    @Test
    public void testSetCfu() {
        giocatore.setCfu(10);
        assertEquals(10, giocatore.getCfu());
    }

    @Test
    public void testGetBorsaNotNull() {
        assertNotNull(giocatore.getBorsa());
    }

    @Test
    public void testSetBorsa() {
        Borsa nuovaBorsa = new Borsa();
        giocatore.setBorsa(nuovaBorsa);
        assertEquals(nuovaBorsa, giocatore.getBorsa());
    }
}
