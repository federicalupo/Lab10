package it.polito.tdp.bar.model;

public class Model {
	
	Simulator s;
	
	public Model () {
		s = new Simulator();
		
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
