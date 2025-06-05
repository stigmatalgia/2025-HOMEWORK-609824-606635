package it.uniroma3.diadia.ambienti;

public class StanzaBloccata extends Stanza{
	
	private Direzione direzioneBloccata;
	private String oggettoSblocca;
	

	public StanzaBloccata(String nome, Direzione direzioneBloccata, String oggettoSblocca) {
		super(nome);
		this.direzioneBloccata = direzioneBloccata;
		this.oggettoSblocca = oggettoSblocca;
	}


	public Direzione getDirezioneBloccata() {
		return direzioneBloccata;
	}
 

	public void setDirezioneBloccata(Direzione direzioneBloccata) {
		this.direzioneBloccata = direzioneBloccata;
	}


	public String getOggettoSblocca() { 
		return oggettoSblocca;
	}


	public void setOggettoSblocca(String oggettoSblocca) {
		this.oggettoSblocca = oggettoSblocca;
	}
	
	@Override
	public Stanza getStanzaAdiacente(Direzione direzione) {
		if (direzione.equals(this.direzioneBloccata) && !this.hasAttrezzo(oggettoSblocca)) {
			return this; // bloccata, non va da nessuna parte
		}
		return super.getStanzaAdiacente(direzione);
	}

	
	@Override
	public String getDescrizione() {
		return super.getDescrizione() + "\n l'oggetto per sbloccare l'uscita a "+ this.direzioneBloccata + " e' "+ this.oggettoSblocca;
	}
	
}
