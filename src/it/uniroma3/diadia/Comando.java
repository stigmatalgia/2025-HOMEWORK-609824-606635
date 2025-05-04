package it.uniroma3.diadia;

public interface Comando {
	//set parametro del comando
	public void setParametro(String parametro);
	//esecuzione comando
	public void esegui(Partita partita);
}
