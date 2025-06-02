package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.ConfigReader;

public class Giocatore {
	static final private int CFU_INIZIALI = ConfigReader.leggiValore("CFU_INIZIALI");
	private int cfu;
	private Borsa borsa = new Borsa();
	
	public Giocatore() {
		this.cfu = CFU_INIZIALI;
	}
	
	public int getCfu() {
		return this.cfu;
	}
	
	public void setCfu(int cfu) {
		this.cfu = cfu;		
	}	
	
	public Borsa getBorsa() {
		return this.borsa;
	}
	
	public void setBorsa(Borsa newBorsa) {
		this.borsa = newBorsa;		
	}	


	
	
}
