package it.polito.tdp.bar.model;

public class Model {
	
	Simulator s;
	
	public Model () {
		s = new Simulator();
		
		//settare qui
		s.aggiungiTavolo(4,5);
		s.aggiungiTavolo(10,2);
		s.aggiungiTavolo(8,4);
		s.aggiungiTavolo(6,4);
		
		//impostare range => opzionale
		s.setMaxDurata(120);
	}

	public void simulazione() {
		s.run();
		
	}
	
	public int getnTotClienti() {
		return s.getnTotClienti();
	}
	public int getnClientiSoddisfatti() {
		return s.getnClientiSoddisfatti();
	}
	public int getnClientiInsoddisfatti() {
		return s.getnClientiInsoddisfatti();
	}
}
