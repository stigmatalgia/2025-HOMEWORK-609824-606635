package test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.StanzaBuia;
import it.uniroma3.diadia.attrezzi.Attrezzo;


class StanzaBuiaTest {
	
	StanzaBuia stanzaBuia = new StanzaBuia("Cantina", "Torcia"); 

    @BeforeEach
    public void setUp() {
       
    }
    
    @Test
    public void testStanzaVisibile() {
    	Attrezzo torcia = new Attrezzo("Torcia",2);
    	stanzaBuia.addAttrezzo(torcia);
    	String descrizioneLuminosa = stanzaBuia.getDescrizione();
    	assertNotEquals(descrizioneLuminosa, "qui c'è buio pesto porta un "+stanzaBuia.getSbloccaLuce());
    }
    
    @Test
    public void testStanzaBuia() {
    	Attrezzo mazza = new Attrezzo("Mazza",6);
    	stanzaBuia.addAttrezzo(mazza);
    	String descrizioneBuia = stanzaBuia.getDescrizione();
    	assertEquals(descrizioneBuia, "qui c'è buio pesto porta un "+stanzaBuia.getSbloccaLuce());
    }
    

}
