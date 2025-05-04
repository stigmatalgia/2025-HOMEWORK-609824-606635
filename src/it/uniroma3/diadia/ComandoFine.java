package it.uniroma3.diadia;

public class ComandoFine implements Comando {
	private IOConsole IO;	
	public ComandoFine(IOConsole IO, Partita partita) {
		this.IO = IO;
	}
	@Override
	public void setParametro(String parametro) {
		return;

	}

	@Override
	public void esegui(Partita partita) {
		IO.mostraMessaggio("Grazie di aver giocato!");  // si desidera smettere
	}

}
