package it.uniroma3.diadia;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ConfigReader {

    private static final String FILE_CONFIG = "diadia.properties";

    static public int leggiValore(String nomeProprieta) {
    	 try (BufferedReader reader = new BufferedReader(new FileReader(FILE_CONFIG))) {
    	        String riga;
    	        while ((riga = reader.readLine()) != null) {
    	            riga = riga.trim();
    	            if (riga.isEmpty() || !riga.contains(":")) continue;

    	            String[] parti = riga.split(":", 2);
    	            if (parti.length == 2 && parti[0].trim().equals(nomeProprieta)) {
    	                return Integer.parseInt(parti[1].trim());
    	            }
    	        }
    	    } catch (IOException e) {
    	        System.err.println("Errore lettura config: " + e.getMessage());
    	    }
    	    return 0;
    }
}
