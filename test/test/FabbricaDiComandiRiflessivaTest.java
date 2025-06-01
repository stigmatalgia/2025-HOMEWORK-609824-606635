package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
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
        labirinto = new LabirintoBuilder()
                .addStanzaIniziale("Atrio").addAttrezzo("osso", 1)
                .addStanzaBuia("Aula N11", "lanterna").addAttrezzo("chiave", 1)
                .addStanza("Aula N10").addAttrezzo("lanterna", 3)
                .addStanzaMagica("Laboratorio Campus", 1)
                .addStanzaBloccata("Biblioteca", "sud", "chiave").addStanzaVincente("Biblioteca")
                .addAdiacenza("Atrio", "Biblioteca", "nord")
                .addAdiacenza("Atrio", "Aula N11", "est")
                .addAdiacenza("Atrio", "Aula N10", "sud")
                .addAdiacenza("Atrio", "Laboratorio Campus", "ovest")
                .addAdiacenza("Aula N11", "Laboratorio Campus", "est")
                .addAdiacenza("Aula N11", "Atrio", "ovest")
                .addAdiacenza("Aula N10", "Atrio", "nord")
                .addAdiacenza("Aula N10", "Aula N11", "est")
                .addAdiacenza("Aula N10", "Laboratorio Campus", "ovest")
                .addAdiacenza("Laboratorio Campus", "Atrio", "est")
                .addAdiacenza("Laboratorio Campus", "Aula N11", "ovest")
                .addAdiacenza("Biblioteca", "Atrio", "sud")
                .getLabirinto();
        partita = new Partita(labirinto);
        factory = new FabbricaDiComandiRiflessiva(IO, partita);
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
        assertEquals("nord", comando.getParametro());
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
