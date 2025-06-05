package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.StringReader;

import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.CaricatoreLabirinto;
import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Stanza;

public class CaricatoreLabirintoTest {

	@Test
	public void testMonolocale() throws Exception {
		String fixture = 
			    "Stanze: salotto\n" +
			    "Stanze buie:\n" +
			    "Stanze magiche:\n" +
			    "Stanze bloccate:\n" +
			    "Inizio: salotto\n" +
			    "Vincente: salotto\n" +
			    "Attrezzi: divano 5 salotto\n" +
			    "Uscite:\n" +
			    "Personaggi:\n";


		CaricatoreLabirinto caricatore = new CaricatoreLabirinto(new StringReader(fixture));
		caricatore.carica();

		Stanza stanzaIniziale = caricatore.getStanzaIniziale();
		assertEquals("salotto", stanzaIniziale.getNome());
		assertEquals(stanzaIniziale, caricatore.getStanzaVincente());
		assertNotNull(stanzaIniziale.getAttrezzo("divano"));
	}

	@Test
	public void testBilocaleConUscita() throws Exception {
		String fixture = 
			    "Stanze: salotto,cucina\n" +
			    "Stanze buie:\n" +
			    "Stanze magiche:\n" +
			    "Stanze bloccate:\n" +
			    "Inizio: salotto\n" +
			    "Vincente: cucina\n" +
			    "Attrezzi:\n" +
			    "Uscite: salotto nord cucina\n" +
			    "Personaggi:\n";


		CaricatoreLabirinto caricatore = new CaricatoreLabirinto(new StringReader(fixture));
		caricatore.carica();

		Stanza salotto = caricatore.getStanzaIniziale();
		assertEquals("salotto", salotto.getNome());
		assertEquals("cucina", salotto.getStanzaAdiacente(Direzione.NORD).getNome());
		assertEquals("cucina", caricatore.getStanzaVincente().getNome());
	}

	@Test
	public void testTrilocaleConAttrezzi() throws Exception {
		String fixture = 
			    "Stanze: ingresso,salone,camera\n" +
			    "Stanze buie:\n" +
			    "Stanze magiche:\n" +
			    "Stanze bloccate:\n" +
			    "Inizio: ingresso\n" +
			    "Vincente: camera\n" +
			    "Attrezzi: chiave 1 salone, libro 2 camera\n" +
			    "Uscite: ingresso est salone, salone nord camera\n" +
			    "Personaggi:\n";


		CaricatoreLabirinto caricatore = new CaricatoreLabirinto(new StringReader(fixture));
		caricatore.carica();

		Stanza ingresso = caricatore.getStanzaIniziale();
		assertEquals("salone", ingresso.getStanzaAdiacente(Direzione.EST).getNome());

		Stanza salone = ingresso.getStanzaAdiacente(Direzione.EST);
		assertNotNull(salone.getAttrezzo("chiave"));

		Stanza camera = salone.getStanzaAdiacente(Direzione.NORD);
		assertNotNull(camera.getAttrezzo("libro"));
		assertEquals(camera, caricatore.getStanzaVincente());
	}
}
