package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.SortedSet;
import java.util.TreeSet;

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
    public void testGetSortedSetOrdinatoPerPeso() {
    	Attrezzo b = new Attrezzo("Camaglio", 5);
    	borsa.addAttrezzo(b);
    	Attrezzo a = new Attrezzo("Archibugio", 5);
    	borsa.addAttrezzo(a);
    
    	assertNotEquals(borsa.getSortedSetOrdinatoPerPeso().toArray()[0], borsa.getSortedSetOrdinatoPerPeso().toArray()[1]);
    }
   
    @Test
    public void testGetContenutoRaggruppatoPerPeso() {
    	Attrezzo b = new Attrezzo("Camaglio", 3);
    	borsa.addAttrezzo(b);
    
    	assertTrue(borsa.getContenutoRaggruppatoPerPeso().containsKey(3));
    }

    @Test    
    public void testGetContenutoOrdinatoPerNome() {
        Attrezzo b = new Attrezzo("Poleaxe", 5);
        borsa.addAttrezzo(b);
        Attrezzo a = new Attrezzo("Brocchiero", 2);
        borsa.addAttrezzo(a);
        Attrezzo c = new Attrezzo("Alabarda", 3);
        borsa.addAttrezzo(c);

        assertEquals(borsa.getContenutoOrdinatoPerNome().toArray()[0], b);
    }


    @Test
    public void testGetContenutoOrdinatoPerPeso() {
    	Attrezzo b = new Attrezzo("Poleaxe", 5);
    	borsa.addAttrezzo(b);
    	Attrezzo a = new Attrezzo("Alabarda", 7);
    	borsa.addAttrezzo(a);
    
    	assertEquals(borsa.getContenutoOrdinatoPerPeso().toArray()[0], b);
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
