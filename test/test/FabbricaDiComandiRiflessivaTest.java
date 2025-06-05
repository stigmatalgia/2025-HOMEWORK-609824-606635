package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.comandi.ComandoAiuto;
import it.uniroma3.diadia.comandi.ComandoFine;
import it.uniroma3.diadia.comandi.ComandoGuarda;
import it.uniroma3.diadia.comandi.ComandoNonValido;
import it.uniroma3.diadia.comandi.ComandoPosa;
import it.uniroma3.diadia.comandi.ComandoPrendi;
import it.uniroma3.diadia.comandi.ComandoVai;
import it.uniroma3.diadia.comandi.FabbricaDiComandiRiflessiva;

class FabbricaDiComandiRiflessivaTest {

	IO IO;
    Partita partita;
    FabbricaDiComandiRiflessiva factory;
    Labirinto labirinto;

    @BeforeEach
    void setUp() { 
        IO = new IOConsole();
        labirinto = Labirinto.newBuilder()
                .addStanzaIniziale("Atrio").addAttrezzo("osso", 1)
                .addStanzaBuia("Aula N11", "lanterna").addAttrezzo("chiave", 1)
                .addStanza("Aula N10").addAttrezzo("lanterna", 3)
                .addStanzaMagica("Laboratorio Campus", 1)
                .addStanzaBloccata("Biblioteca", Direzione.SUD, "chiave").addStanzaVincente("Biblioteca")
                .addAdiacenza("Atrio", "Biblioteca", Direzione.NORD)
                .addAdiacenza("Atrio", "Aula N11", Direzione.EST)
                .addAdiacenza("Atrio", "Aula N10", Direzione.SUD)
                .addAdiacenza("Atrio", "Laboratorio Campus", Direzione.OVEST)
                .addAdiacenza("Aula N11", "Laboratorio Campus", Direzione.EST)
                .addAdiacenza("Aula N11", "Atrio", Direzione.OVEST)
                .addAdiacenza("Aula N10", "Atrio", Direzione.NORD)
                .addAdiacenza("Aula N10", "Aula N11", Direzione.EST)
                .addAdiacenza("Aula N10", "Laboratorio Campus", Direzione.OVEST)
                .addAdiacenza("Laboratorio Campus", "Atrio", Direzione.EST)
                .addAdiacenza("Laboratorio Campus", "Aula N11", Direzione.OVEST)
                .addAdiacenza("Biblioteca", "Atrio", Direzione.SUD)
                .getLabirinto();
        partita = new Partita(labirinto);
        factory = new FabbricaDiComandiRiflessiva();
    }

    @Test
    void testComandoPrendi() {
        ComandoPrendi comando = (ComandoPrendi)factory.costruisciComando("prendi chiave");
        assertEquals("prendi", comando.getNome());
        assertEquals("chiave", comando.getParametro());
    } 

    @Test
    void testComandoPosa() {
    	ComandoPosa comando = (ComandoPosa)factory.costruisciComando("posa libro");
        assertEquals("posa", comando.getNome());
        assertEquals("libro", comando.getParametro());
    }

    @Test
    void testComandoVai() {
        ComandoVai comando = (ComandoVai)factory.costruisciComando("vai nord");
        assertEquals("vai", comando.getNome());
        assertEquals("NORD", comando.getParametro());
    }

    @Test
    void testComandoAiuto() {
    	ComandoAiuto comando = (ComandoAiuto)factory.costruisciComando("aiuto");
        assertEquals("aiuto", comando.getNome());
        assertNull(comando.getParametro());
    }

    @Test
    void testComandoFine() {
        ComandoFine comando = (ComandoFine)factory.costruisciComando("fine");
        assertEquals("fine", comando.getNome());
        assertNull(comando.getParametro());
    }

    @Test
    void testComandoGuarda() {
        ComandoGuarda comando = (ComandoGuarda)factory.costruisciComando("guarda");
        assertEquals("guarda", comando.getNome());
        assertNull(comando.getParametro());
    }

    @Test
    void testComandoNonValido() {
    	ComandoNonValido comando = (ComandoNonValido)factory.costruisciComando("salta su");
        assertNull(comando.getNome());
        assertNull(comando.getParametro());
    }

    @Test
    void testComandoVuoto() {
    	ComandoNonValido comando = (ComandoNonValido)factory.costruisciComando("");
        assertNull(comando.getNome());
        assertNull(comando.getParametro());
    }
}
