package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.ComandoVai;

public class ComandoVaiTest {

	Partita Partita;
	ComandoVai Comando = null;
	IO IO;
	@BeforeEach
	void setUp() {
		Labirinto labuilder = new LabirintoBuilder()
				.addStanzaIniziale("LabCampusOne")
				.addStanzaVincente("Biblioteca")
				.addAdiacenza("LabCampusOne", "Biblioteca", "ovest")
				.getLabirinto(); 
		Partita = new Partita(labuilder);
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
		Comando.setParametro("ovest");
		Comando.esegui(this.Partita, IO);
		assertNotEquals(this.Partita.getLabirinto().getStanzaCorrente(),this.Partita.getLabirinto().getStanzaIniziale());
	}

}