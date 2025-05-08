package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.ComandoVai;

public class ComandoVaiTest {

	Partita Partita;
	Comando Comando = null;
	IO IO;
	@BeforeEach
	void setUp() {
		Partita = new Partita();
		Comando = new ComandoVai();
		IO =new IOConsole();
	}
	
	
	@Test
	void testVaiFallisce() {
		Comando.setParametro("sopra");
		Comando.esegui(this.Partita, IO);
		assertEquals(this.Partita.getLabirinto().getStanzaCorrente(),this.Partita.getLabirinto().getStanzaIniziale());
	}
	
	@Test
	void testVai() {
		Comando.setParametro("nord");
		Comando.esegui(this.Partita, IO);
		assertNotEquals(this.Partita.getLabirinto().getStanzaCorrente(),this.Partita.getLabirinto().getStanzaIniziale());
	}

}