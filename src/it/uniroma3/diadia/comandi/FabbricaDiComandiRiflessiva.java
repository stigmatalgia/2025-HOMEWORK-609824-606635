package it.uniroma3.diadia.comandi;

import java.util.Scanner;

public class FabbricaDiComandiRiflessiva implements FabbricaDiComandi, AutoCloseable{
	public FabbricaDiComandiRiflessiva() {
	}
	@Override
	public AbstractComando costruisciComando(String istruzione){

		try (Scanner scannerDiParole = new Scanner(istruzione)) {
			String nomeComando = null; // es. ‘vai’
			String parametro = null; // es. ‘sud’
			AbstractComando comando = null;
			if (scannerDiParole.hasNext())
				nomeComando = scannerDiParole.next();//prima parola: nome del comando
			if (scannerDiParole.hasNext())
				parametro = scannerDiParole.next();//seconda parola: eventuale parametro

			try {
				String nomeClasse = "it.uniroma3.diadia.comandi.Comando";
				nomeClasse += Character.toUpperCase(nomeComando.charAt(0));
				nomeClasse += nomeComando.substring(1);
				comando = (AbstractComando)Class.forName(nomeClasse).newInstance();
				comando.setParametro(parametro);
			} catch (Exception e) {
				comando = new ComandoNonValido();
			}
			return comando;
		}
	}
	@Override
	public void close() throws Exception {
	}
}
