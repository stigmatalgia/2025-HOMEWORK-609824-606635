package it.uniroma3.diadia.ambienti;

public enum Direzione {
	NORD, SUD, EST, OVEST;
	
	public static Direzione fromString(String direzione) {
		if(direzione == null) {
			throw new IllegalArgumentException("null non è una direzione");
		} try {
			return Direzione.valueOf(direzione.toUpperCase());
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Direzione non valida");
		}
	}
}
