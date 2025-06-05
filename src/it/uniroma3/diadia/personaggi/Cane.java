package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Cane extends AbstractPersonaggio {
	private static final String MESSAGGIO_MORSO = "Il cane ti morde, hai perso un CFU! spera di averne abbastanza per il piano di studio. ";
	private static final String MESSAGGIO_REGALO1 = "Il cane accetta il regalo e lascia un ";
	private static final String MESSAGGIO_REGALO2 = " a terra!";
	private String ciboPreferito;
	private Attrezzo attrezzo; //quello che butta a terra
	private Attrezzo slotRegalo; //quello che riceve
	
	public Cane(String nome, String presentazione, String ciboPreferito, Attrezzo attrezzo) {
		super(nome, presentazione);
		this.ciboPreferito = ciboPreferito;
		this.attrezzo = attrezzo;
	}

	@Override
	public String agisci(Partita partita) {
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu()-1);
		return MESSAGGIO_MORSO;
	}

	@Override
	public String riceviRegalo(Attrezzo regalo, Partita partita) {
		this.slotRegalo = regalo;
		partita.getGiocatore().getBorsa().removeAttrezzo(regalo.getNome());
		if(slotRegalo.getNome().equals(ciboPreferito)) {
			partita.getStanzaCorrente().addAttrezzo(this.attrezzo);
			return MESSAGGIO_REGALO1+this.attrezzo+MESSAGGIO_REGALO2;
		}
		else {
			return MESSAGGIO_MORSO;
		}
		
	}
}