package it.uniroma3.diadia;

import java.util.Scanner;

public class IOConsole implements IO, AutoCloseable {
	private final Scanner scanner;
	
	public IOConsole() {
        this.scanner = new Scanner(System.in);
    }
	
	@Override
    public void mostraMessaggio(String msg) {
        System.out.println(msg);
    }

    @Override
    public String leggiRiga() {
        return scanner.nextLine();
    }

    public void close() {
        scanner.close();
    }
}
