package it.uniroma3.diadia.giocatore;

import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;

import it.uniroma3.diadia.ConfigReader;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Borsa {
    public static final int DEFAULT_PESO_MAX_BORSA = ConfigReader.leggiValore("DEFAULT_PESO_MAX_BORSA");
    
    private Map<String, Attrezzo> attrezzi;
    private int pesoMax;

    public Borsa() {
        this(DEFAULT_PESO_MAX_BORSA);
    }

    public Borsa(int pesoMax) {
        this.pesoMax = pesoMax;
        this.attrezzi = new LinkedHashMap<>();
    }

    public boolean addAttrezzo(Attrezzo attrezzo) {
        if (attrezzo == null) {
            return false;
        }
        if (this.getPeso() + attrezzo.getPeso() > this.pesoMax) {
            return false;
        }
        this.attrezzi.put(attrezzo.getNome(), attrezzo);
        return true;
    }

    public int getPesoMax() {
        return this.pesoMax;
    }

    public Attrezzo getAttrezzo(String nomeAttrezzo) {
        return this.attrezzi.get(nomeAttrezzo);
    }

    public int getPeso() {
        int peso = 0;
        for (Attrezzo attrezzo : this.attrezzi.values()) {
            peso += attrezzo.getPeso();
        }
        return peso;
    }

    public boolean isEmpty() {
        return this.attrezzi.isEmpty();
    }

    public boolean hasAttrezzo(String nomeAttrezzo) {
        return this.attrezzi.containsKey(nomeAttrezzo);
    }

    public Attrezzo removeAttrezzo(String nomeAttrezzo) {
        return this.attrezzi.remove(nomeAttrezzo);
    }
    
    public List<Attrezzo> getContenutoOrdinatoPerPeso() {
        List<Attrezzo> temp = new ArrayList<Attrezzo>();
        temp.addAll(this.attrezzi.values());
        
        temp.sort(new Comparator<Attrezzo>() {
            @Override
            public int compare(Attrezzo a1, Attrezzo a2) {
                return Integer.compare(a1.getPeso(), a2.getPeso());
            }
        });
        return temp;
    }
    
    public Set<Attrezzo> getContenutoOrdinatoPerNome() {
        Set<Attrezzo> temp = new TreeSet<>(new Comparator<Attrezzo>() {
            @Override
            public int compare(Attrezzo a1, Attrezzo a2) {
                return a2.getNome().compareTo(a1.getNome());
            }
        });
        temp.addAll(this.attrezzi.values());
        return temp;
    }

    public Map<Integer,Set<Attrezzo>> getContenutoRaggruppatoPerPeso(){
    	Map<Integer,Set<Attrezzo>> temp = new HashMap<Integer,Set<Attrezzo>>();
    	for(Attrezzo a : this.attrezzi.values()) {
    		
    		if(!temp.containsKey(a.getPeso())) {
    			temp.put(a.getPeso(), new HashSet<Attrezzo>());
    		}
    		Set<Attrezzo> s = temp.get(a.getPeso());
    		s.add(a);
    		
    	}
    	return temp;
    }

    public SortedSet<Attrezzo> getSortedSetOrdinatoPerPeso() {
    	SortedSet<Attrezzo> temp = new TreeSet<>(new Comparator<Attrezzo>() {
            @Override
            public int compare(Attrezzo a1, Attrezzo a2) {
                if(a1.getPeso() == a2.getPeso())
                {
                	return a1.getNome().compareTo(a2.getNome());
                }
                return a1.getPeso() - a2.getPeso();
            }
        });
        temp.addAll(this.attrezzi.values());
        return temp;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.isEmpty()) {
            sb.append("Borsa vuota");
        } else {
            sb.append("Contenuto borsa (")
              .append(this.getPeso())
              .append("kg/")
              .append(this.pesoMax)
              .append("kg): ");
            sb.append(this.getSortedSetOrdinatoPerPeso().toString());
        }
        return sb.toString();
    }
}