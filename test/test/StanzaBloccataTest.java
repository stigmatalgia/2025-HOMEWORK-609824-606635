package test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.ambienti.StanzaBloccata;
import it.uniroma3.diadia.attrezzi.Attrezzo;


class StanzaBloccataTest {
	
	StanzaBloccata stanzaBloccata = new StanzaBloccata("Cantina", Direzione.EST, "chiave"); 

    @BeforeEach
    public void setUp() {
    	Stanza segrete = new Stanza("Segrete");
    	segrete.impostaStanzaAdiacente(stanzaBloccata.getDirezioneBloccata(), stanzaBloccata);
       
    }
    
    @Test
    public void testStanzaAperta() {
    	Attrezzo chiave = new Attrezzo("chiave",1);
    	stanzaBloccata.addAttrezzo(chiave);
    	assertNotEquals(stanzaBloccata, stanzaBloccata.getStanzaAdiacente(stanzaBloccata.getDirezioneBloccata()));
    }
    
    @Test
    public void testStanzaChiusa() {
    	assertEquals(stanzaBloccata, stanzaBloccata.getStanzaAdiacente(stanzaBloccata.getDirezioneBloccata()));
    }
    

}
