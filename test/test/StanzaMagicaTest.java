package test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.StanzaMagica;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaMagicaTest {

	private StanzaMagica stanza;
	private Attrezzo attrezzo;
    @BeforeEach
    public void setUp() {
		stanza = new StanzaMagica("planetario", 1);
		attrezzo = new Attrezzo("cilindro", 3);
	}
	
	@Test
	public void testOggettoDiventaMagicoDopoSoglia() {

		stanza.addAttrezzo(attrezzo); // primo inserimento, normale
		stanza.removeAttrezzo(attrezzo);

		stanza.addAttrezzo(attrezzo); // secondo inserimento, magico
		Attrezzo attrezzoMagico = stanza.getAttrezzo("ordnilic");

		assertNotNull(attrezzoMagico);
		assertEquals("ordnilic", attrezzoMagico.getNome());
		assertEquals(6, attrezzoMagico.getPeso());
	}
}
