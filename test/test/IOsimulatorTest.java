package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;

class IOsimulatorTest {
    private Labirinto labirinto;

    @BeforeEach
    void setUp() {
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
    }

    @Test
    void testPartitaFinita() {
        List<String> comandi = Arrays.asList("fine");
        IOSimulator io = new IOSimulator(comandi);
        DiaDia gioco = new DiaDia(io, labirinto);
        gioco.gioca();

        List<String> output = io.getOutputStrings();
        assertEquals("Grazie di aver giocato!", output.get(output.size() - 1));
        assertTrue(gioco.getPartita().isFinita());
    }

    @Test
    void testPosaPrendiPartita() {
        List<String> comandi = Arrays.asList("prendi osso", "vai sud", "prendi lanterna", "vai ovest", "posa lanterna", "posa osso");
        IOSimulator io = new IOSimulator(comandi);
        DiaDia gioco = new DiaDia(io, labirinto);
        gioco.gioca();

        assertTrue(gioco.getPartita().getStanzaCorrente().hasAttrezzo("osso"));
        assertTrue(gioco.getPartita().getStanzaCorrente().hasAttrezzo("lanterna"));
        assertEquals("Laboratorio Campus", gioco.getPartita().getStanzaCorrente().getNome());
    }

    @Test
    void testPartitaVintaCercandoChiave() {
        List<String> comandi = Arrays.asList(
            "vai sud", "prendi lanterna", "vai nord", "vai est", "posa lanterna",
            "prendi chiave", "prendi lanterna", "vai ovest", "vai nord"
        );
        IOSimulator io = new IOSimulator(comandi);
        DiaDia gioco = new DiaDia(io, labirinto);
        gioco.gioca();

        assertTrue(gioco.getPartita().getGiocatore().getBorsa().hasAttrezzo("chiave"));
        assertEquals("Biblioteca", gioco.getPartita().getStanzaCorrente().getNome());
    }

    @Test
    void testStanzaMagica() {
        List<String> comandi = Arrays.asList(
            "vai sud", "prendi lanterna", "vai ovest", "posa lanterna", "prendi lanterna",
            "posa lanterna", "prendi lanterna", "posa lanterna", "prendi lanterna", "posa lanterna"
        );
        IOSimulator io = new IOSimulator(comandi);
        DiaDia gioco = new DiaDia(io, labirinto);
        gioco.gioca();

        assertEquals("Laboratorio Campus", gioco.getPartita().getStanzaCorrente().getNome());
        assertTrue(gioco.getPartita().getStanzaCorrente().hasAttrezzo("anretnal"));
    }
}
