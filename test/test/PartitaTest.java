package test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

public class PartitaTest {

    private Partita partita;

    @BeforeEach
    public void setUp() {
        partita = new Partita();
    }

    @Test
    public void testPartitaInizialmenteNonFinita() {
        assertFalse(partita.isFinita());
    }

    @Test
    public void testSetFinita() {
        partita.setFinita();
        assertTrue(partita.isFinita());
    }

    @Test
    public void testPartitaVinta() {
        Stanza stanzaVincente = new Stanza("Uscita");
        partita.getLabirinto().setStanzaCorrente(stanzaVincente);
        partita.getLabirinto().setStanzaVincente(stanzaVincente);
        assertTrue(partita.vinta());
        assertTrue(partita.isFinita());
    }

    @Test
    public void testPartitaFinitaPerCfu() {
        partita.getGiocatore().setCfu(0);
        assertTrue(partita.isFinita());
    }
    
}
