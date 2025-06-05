package it.uniroma3.diadia.personaggi;

import java.util.Collection;
import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Strega extends AbstractPersonaggio {
	private static final String MESSAGGIO_TELEPORT = "Hai noclippato in ";
	private static final String MESSAGGIO_REGALO = "MA QUANDO MAI 🤣🤣🤣 ";
	private Attrezzo slotRegalo;
	public Strega(String nome, String presentazione) {
		super(nome, presentazione);
	}

	@Override
	public String agisci(Partita partita) {
		Collection<Stanza> adiacenti = partita.getStanzaCorrente().getMapStanzeAdiacenti().values();
		SortedSet<Stanza> stanzeAdiacenti = new TreeSet<>(new Comparator<Stanza>() {
            @Override
            public int compare(Stanza s1, Stanza s2) {
                return s1.getAttrezzi().size() - s2.getAttrezzi().size();
            }
        });
		stanzeAdiacenti.addAll(adiacenti);

		Stanza temp = stanzeAdiacenti.first();
		if (this.haSalutato()) {
			temp = stanzeAdiacenti.last();
		}
		partita.setStanzaCorrente(temp);
		return MESSAGGIO_TELEPORT + temp.getNome();
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		this.slotRegalo = attrezzo;
		return MESSAGGIO_REGALO;
	}

}