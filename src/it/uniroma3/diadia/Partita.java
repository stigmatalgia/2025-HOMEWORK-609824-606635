package it.uniroma3.diadia;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Giocatore;

/**
 * Questa classe modella una partita del gioco
 *
 * @author  docente di POO
 * @see Stanza
 * @version base
 */		

public class Partita {

	private boolean finita;
	private Labirinto labirinto;
	private Giocatore giocatore = new Giocatore();
	
	/*
	 * costruttore di partita
	 */
	public Partita(Labirinto labirinto){
		this.finita = false;
		this.labirinto = labirinto;
	}
	
	/**
	 * Restituisce vero se e solo se la partita e' stata vinta
	 * @return vero se partita vinta
	 */
	public boolean vinta() {
		return labirinto.getStanzaCorrente()== labirinto.getStanzaVincente();
	}

	/**
	 * Restituisce vero se e solo se la partita e' finita
	 * @return vero se partita finita
	 */
	public boolean isFinita() {
		return finita || vinta() || (giocatore.getCfu() <= 0);
	}

	/**
	 * Imposta la partita come finita
	 *
	 */
	public void setFinita() {
		this.finita = true;
	}
	public Labirinto getLabirinto() {
		return this.labirinto;
	}
	
	public Giocatore getGiocatore() {
		return this.giocatore;
	}
	
	public void setLabirinto(Labirinto newLabirinto) {
		this.labirinto = newLabirinto;
	}
	
	public void setGiocatore(Giocatore newGiocatore) {
		this.giocatore = newGiocatore;
	}

}
