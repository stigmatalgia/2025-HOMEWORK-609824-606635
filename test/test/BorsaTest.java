package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.uniroma3.diadia.giocatore.Borsa;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class BorsaTest {

    private Borsa borsa;

    @BeforeEach
    public void setUp() {
        borsa = new Borsa();
    }

    @Test
    public void testDefaultPesoMax() {
        assertEquals(Borsa.DEFAULT_PESO_MAX_BORSA, borsa.getPesoMax());
    }

    @Test
    public void testAddAttrezzo() {
    	Attrezzo a = new Attrezzo("Archibugio", 5);
    	assertTrue(borsa.addAttrezzo(a));
    }

    @Test
    public void testAddAttrezzoTroppoPeso() {
    	Attrezzo a = new Attrezzo("Torre d'assalto", 1000);
    	assertFalse(borsa.addAttrezzo(a));
    }

    @Test
    public void testRemoveAttrezzo() {
    	Attrezzo a = new Attrezzo("Azza Italiana", 3);
    	borsa.addAttrezzo(a);
    	assertEquals(borsa.removeAttrezzo(a.getNome()), a);
    }

    @Test
    public void testBorsaVuota() {
    	assertTrue(borsa.isEmpty());
    }

    @Test
    public void testToStringEmpty() {
        assertEquals("Borsa vuota", borsa.toString());
    }
}
