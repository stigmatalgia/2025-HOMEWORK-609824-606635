package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.FabbricaDiComandiFisarmonica;

class FabbricaDiComandiFisarmonicaTest {

	IO IO;
    Partita partita;
    FabbricaDiComandiFisarmonica factory;

    @BeforeEach
    void setUp() { 
        IO = new IOConsole();
        partita = new Partita();
        factory = new FabbricaDiComandiFisarmonica(IO, partita);
    }

    @Test
    void testComandoPrendi() {
        Comando comando = factory.costruisciComando("prendi chiave");
        assertEquals("prendi", comando.getNome());
        assertEquals("chiave", comando.getParametro());
    }

    @Test
    void testComandoPosa() {
        Comando comando = factory.costruisciComando("posa libro");
        assertEquals("posa", comando.getNome());
        assertEquals("libro", comando.getParametro());
    }

    @Test
    void testComandoVai() {
        Comando comando = factory.costruisciComando("vai nord");
        assertEquals("vai", comando.getNome());
        assertEquals("nord", comando.getParametro());
    }

    @Test
    void testComandoAiuto() {
        Comando comando = factory.costruisciComando("aiuto");
        assertEquals("aiuto", comando.getNome());
        assertNull(comando.getParametro());
    }

    @Test
    void testComandoFine() {
        Comando comando = factory.costruisciComando("fine");
        assertEquals("fine", comando.getNome());
        assertNull(comando.getParametro());
    }

    @Test
    void testComandoGuarda() {
        Comando comando = factory.costruisciComando("guarda");
        assertEquals("guarda", comando.getNome());
        assertNull(comando.getParametro());
    }

    @Test
    void testComandoNonValido() {
        Comando comando = factory.costruisciComando("salta su");
        assertNull(comando.getNome());
        assertNull(comando.getParametro());
    }

    @Test
    void testComandoVuoto() {
        Comando comando = factory.costruisciComando("");
        assertNull(comando.getNome());
        assertNull(comando.getParametro());
    }
}
