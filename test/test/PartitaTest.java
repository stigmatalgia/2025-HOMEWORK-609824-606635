package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;

public class PartitaTest {

    private Partita partita;
    private Labirinto labirinto;

    @BeforeEach
    public void setUp() {
        // Creo un labirinto minimo con stanza iniziale e stanza vincente
        this.labirinto = Labirinto.newBuilder()
            .addStanzaIniziale("Atrio")
            .addStanzaVincente("Uscita")
            .addAdiacenza("Atrio", "Uscita", Direzione.NORD)
            .getLabirinto();

        this.partita = new Partita(this.labirinto);
    }

    @Test
    public void testPartitaInizialmenteNonFinita() {
        assertFalse(partita.isFinita(), "Una nuova partita non dovrebbe essere finita");
    }

    @Test
    public void testSetFinita() {
        partita.setFinita();
        assertTrue(partita.isFinita(), "Dopo setFinita(), la partita dovrebbe essere finita");
    }

    @Test
    public void testPartitaVinta() {
        // Imposto la stanza vincente anche come corrente
        Stanza uscita = labirinto.getStanzaVincente();
        partita.setStanzaCorrente(uscita);

        assertTrue(partita.vinta(), "La partita dovrebbe risultare vinta quando il giocatore è nella stanza vincente");
        assertTrue(partita.isFinita(), "La partita dovrebbe risultare finita se è vinta");
    }

    @Test
    public void testPartitaFinitaPerCfu() {
        partita.getGiocatore().setCfu(0);
        assertTrue(partita.isFinita(), "La partita dovrebbe risultare finita se i CFU sono 0");
    }
}
