package test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaTest {

    private Stanza stanza;

    @BeforeEach
    public void setUp() {
        stanza = new Stanza("Atrio");
    }

    @Test
    public void testImpostaERecuperaStanzaAdiacente() {
        Stanza laboratorio = new Stanza("Laboratorio");
        stanza.impostaStanzaAdiacente(Direzione.NORD, laboratorio);
        assertEquals(laboratorio, stanza.getStanzaAdiacente(Direzione.NORD));
    }

    @Test
    public void testAddAttrezzo() {
        Attrezzo martello = new Attrezzo("Martello", 3);
        assertTrue(stanza.addAttrezzo(martello));
    }
    
    @Test
    public void testHasAttrezzo() {
    	Attrezzo martello = new Attrezzo("Martello", 3);
    	stanza.addAttrezzo(martello);
    	assertTrue(stanza.hasAttrezzo("Martello"));        
    }
    
    @Test
    public void testPiuDiDieciAttrezzi() {
    	Attrezzo oggetto = new Attrezzo("Oggetto", 3);
        for (int i = 0; i < 11; i++) {
            stanza.addAttrezzo(oggetto);
        }
    }
    
    @Test
    public void testRemoveAttrezzo() {
        Attrezzo lanterna = new Attrezzo("Lanterna", 1);
        stanza.addAttrezzo(lanterna);
        assertTrue(stanza.removeAttrezzo(lanterna));
    }
}
