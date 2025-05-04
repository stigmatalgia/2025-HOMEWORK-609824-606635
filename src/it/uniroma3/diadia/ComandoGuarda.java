package it.uniroma3.diadia;

public class ComandoGuarda implements Comando {
	private IOConsole IO;
	
	public ComandoGuarda(IOConsole IO, Partita partita) {
		this.IO = IO;
	}
	@Override
	public void setParametro(String parametro) {
		return;

	}

	@Override
	public void esegui(Partita partita) {
		IO.mostraMessaggio(partita.getLabirinto().getStanzaCorrente().getDescrizione());
		IO.mostraMessaggio("CFU rimasti:" + partita.getGiocatore().getCfu() + "");
	}

}
