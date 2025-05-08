package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaMagicaTest {

	private StanzaMagica stanzaMagica;

	@BeforeEach
	void setUp() {
		stanzaMagica = new StanzaMagica("Stanza Magica", 3);
	}


	@Test
	void testModificaAttrezzo() {
		Attrezzo attrezzo = new Attrezzo("spada", 3);
		stanzaMagica.addAttrezzo(new Attrezzo("a", 1));
		stanzaMagica.addAttrezzo(new Attrezzo("b", 1));
		stanzaMagica.addAttrezzo(new Attrezzo("c", 1));
		stanzaMagica.addAttrezzo(attrezzo); // dovrebbe essere modificato

		Attrezzo modificato = stanzaMagica.getAttrezzo("adaps");
		assertNotNull(modificato);
		assertEquals(6, modificato.getPeso());
	}

}
