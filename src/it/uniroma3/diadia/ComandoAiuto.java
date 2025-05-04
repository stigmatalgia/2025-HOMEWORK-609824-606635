package it.uniroma3.diadia;

public class ComandoAiuto implements Comando {
	private IOConsole IO;
	static final private String[] elencoComandi = {"vai", "aiuto", "fine", "prendi", "posa"};

	
	public ComandoAiuto(IOConsole IO, Partita partita) {
		this.IO = IO;
	}

	@Override
	public void setParametro(String parametro) {
		return;

	}

	@Override
	public void esegui(Partita partita) {
		String s = "";
		for(int i=0; i< elencoComandi.length; i++) {
			s+=elencoComandi[i]+" ";}
		IO.mostraMessaggio(s);
		IO.mostraMessaggio("");

	}

}
