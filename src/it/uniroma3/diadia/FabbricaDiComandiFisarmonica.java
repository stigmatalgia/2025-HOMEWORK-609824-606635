package it.uniroma3.diadia;

import java.util.Scanner;

public class FabbricaDiComandiFisarmonica implements FabbricaDiComandi{
	private IOConsole IO;
	private Partita partita;

	public FabbricaDiComandiFisarmonica(IOConsole IO, Partita partita) {
		this.IO = IO;
		this.partita = partita;
	}

	@Override
	public Comando costruisciComando(String istruzione) {
		Scanner scannerDiParole = new Scanner(istruzione);
		String nomeComando = null;
		String parametro = null;
		Comando comando = null;
		if (scannerDiParole.hasNext())
			nomeComando = scannerDiParole.next(); // prima parola: nome del comando
		if (scannerDiParole.hasNext())
			parametro = scannerDiParole.next(); // seconda parola: eventuale parametro
		if (nomeComando == null)
			comando = new ComandoNonValido(IO, null);
		else if (nomeComando.equals("vai"))
			comando = new ComandoVai(IO, partita);
		else if (nomeComando.equals("prendi"))
			comando = new ComandoPrendi(IO, partita);
		else if (nomeComando.equals("posa"))
			comando = new ComandoPosa(IO, partita);
		else if (nomeComando.equals("aiuto"))
			comando = new ComandoAiuto(IO, null);
		else if (nomeComando.equals("fine"))
			comando = new ComandoFine(IO, null);
		else if (nomeComando.equals("guarda"))
			comando = new ComandoGuarda(IO, partita);
			else comando = new ComandoNonValido(IO, partita);
		comando.setParametro(parametro);
		return comando;
	}
}
