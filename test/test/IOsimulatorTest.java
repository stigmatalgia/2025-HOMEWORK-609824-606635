package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.Partita;

class IOsimulatorTest {
	
	@BeforeEach 
	void SetUp() {

 
	}
	
	@Test
	void testPartitaFinita() {
		String []Comandi = {"fine"};
		IOSimulator IO = new IOSimulator(Comandi);
		DiaDia Test = new DiaDia(IO);
		Test.gioca();
		
		String UltimoMessaggio = IO.getOutputStrings()[Comandi.length];
		
		assertEquals(UltimoMessaggio,"Grazie di aver giocato!");
		assertTrue(Test.getPartita().isFinita());
	}
	
	
	@Test
	void testPosaPrendiPartita() {
		String []Comandi = {"prendi osso","vai sud", "prendi lanterna", "vai ovest", "posa lanterna", "posa osso"};
		IOSimulator IO = new IOSimulator(Comandi);
		DiaDia Test = new DiaDia(IO);
		Test.gioca();
		
		assertTrue(Test.getPartita().getLabirinto().getStanzaCorrente().hasAttrezzo("osso"));
		assertTrue(Test.getPartita().getLabirinto().getStanzaCorrente().hasAttrezzo("lanterna"));
		assertEquals(Test.getPartita().getLabirinto().getStanzaCorrente().getNome(),"Laboratorio Campus");
	}
	
	
	@Test
	void testPartitaVintaCercandoChiave() {
		String []Comandi = {"vai sud", "prendi lanterna", "vai nord", "vai est", "posa lanterna", "prendi chiave", "prendi lanterna", "vai ovest", "vai nord"};
		IOSimulator IO = new IOSimulator(Comandi);
		DiaDia Test = new DiaDia(IO);
		Test.gioca();
		
		assertTrue(Test.getPartita().getGiocatore().getBorsa().hasAttrezzo("chiave"));
		assertEquals(Test.getPartita().getLabirinto().getStanzaCorrente().getNome(),"Biblioteca");
	}
	
	@Test
	void testStanzaMagica() {
		String []Comandi = {"vai sud", "prendi lanterna", "vai ovest","posa lanterna","prendi lanterna","posa lanterna","prendi lanterna", "posa lanterna","prendi lanterna", "posa lanterna"};
		IOSimulator IO = new IOSimulator(Comandi);
		DiaDia Test = new DiaDia(IO);
		Test.gioca();
		

		assertEquals(Test.getPartita().getLabirinto().getStanzaCorrente().getNome(),"Laboratorio Campus");
		assertTrue(Test.getPartita().getLabirinto().getStanzaCorrente().hasAttrezzo("anretnal"));
	}
	
	
	
	 

}



