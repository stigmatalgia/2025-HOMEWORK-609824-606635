package it.uniroma3.diadia;

public class ComandoNonValido implements Comando{
	private IOConsole IO;
	
	public ComandoNonValido(IOConsole IO, Partita partita) {
		this.IO = IO;
	}
	
	@Override
	public void setParametro(String parametro) {
		return;
		
	}

	@Override
	public void esegui(Partita partita) {
		IO.mostraMessaggio("Comando non valido!");
	}
}
