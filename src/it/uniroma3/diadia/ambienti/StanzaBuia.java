package it.uniroma3.diadia.ambienti;

public class StanzaBuia extends Stanza{
	
	private String sbloccaLuce;
	
	public StanzaBuia(String nome, String sbloccaLuce) {
		super(nome);
		this.setSbloccaLuce(sbloccaLuce);
	}

	public String getSbloccaLuce() {
		return sbloccaLuce;
	}

	public void setSbloccaLuce(String sbloccaLuce) {
		this.sbloccaLuce = sbloccaLuce;
	}
	
	@Override
	public String getDescrizione() {
	    if (super.getAttrezzo(sbloccaLuce) != null) {
	        return super.getDescrizione();
	    } else {
	        return "qui c'è buio pesto porta un " + this.getSbloccaLuce();
	    }
	}


}
