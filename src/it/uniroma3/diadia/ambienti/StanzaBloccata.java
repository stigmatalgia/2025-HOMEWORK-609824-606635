package it.uniroma3.diadia.ambienti;

public class StanzaBloccata extends Stanza{
	
	private String direzioneBloccata;
	private String oggettoSblocca;
	

	public StanzaBloccata(String nome, String direzioneBloccata, String oggettoSblocca) {
		super(nome);
		this.direzioneBloccata = direzioneBloccata;
		this.oggettoSblocca = oggettoSblocca;
	}


	public String getDirezioneBloccata() {
		return direzioneBloccata;
	}
 

	public void setDirezioneBloccata(String direzioneBloccata) {
		this.direzioneBloccata = direzioneBloccata;
	}


	public String getOggettoSblocca() { 
		return oggettoSblocca;
	}


	public void setOggettoSblocca(String oggettoSblocca) {
		this.oggettoSblocca = oggettoSblocca;
	}
	
	@Override
	public Stanza getStanzaAdiacente(String dir) {
		if(super.hasAttrezzo(oggettoSblocca)) {
			return super.getStanzaAdiacente(dir);
		} else {
			return this;
		}
	}
	
	@Override
	public String getDescrizione() {
		return super.getDescrizione() + "\n l'oggetto per sbloccare l'uscita di "+ this.direzioneBloccata + "e' "+ this.oggettoSblocca;
	}
	
}
