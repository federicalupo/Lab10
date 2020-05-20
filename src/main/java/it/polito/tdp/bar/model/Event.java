package it.polito.tdp.bar.model;

import java.time.LocalTime;

public class Event implements Comparable<Event> {

	public enum EventType{
		ARRIVO_GRUPPO_CLIENTI, TAVOLO_LIBERATO
	}
	
	private LocalTime time;
	private  EventType eventype;
	private int nPersone;
	private float tolleranza; //variabile
	private int chiaveTavoloLiberato;
	
	public Event(LocalTime time, EventType eventype, int nPersone, float tolleranza) {
		super();
		this.time = time;
		this.eventype = eventype;
		this.nPersone = nPersone;
		
		this.tolleranza = tolleranza;
	}
//dubbio due costruttori??
	/**COSTRUTTORE TAVOLO_LIBERATO
	 * 
	 * @param time
	 * @param eventype
	 * @param chiaveTavoloLiberato => in modo da aggiornare numero tavoli con quel nPosti
	 */
	public Event(LocalTime time, EventType eventype, Integer chiaveTavoloLiberato) {
		super();
		this.time = time;
		this.eventype = eventype;
		this.chiaveTavoloLiberato= chiaveTavoloLiberato;
		
	}
	
	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public EventType getEventype() {
		return eventype;
	}

	public void setEventype(EventType eventype) {
		this.eventype = eventype;
	}

	public int getnPersone() {
		return nPersone;
	}

	public void setnPersone(int nPersone) {
		this.nPersone = nPersone;
	}

	

	public float getTolleranza() {
		return tolleranza;
	}

	public void setTolleranza(float tolleranza) {
		this.tolleranza = tolleranza;
	}

	@Override
	public int compareTo(Event e) {
		return this.time.compareTo(e.getTime());
	}
	
	public int getChiaveTavoloLiberato() {
		return chiaveTavoloLiberato;
	}
	
	
	
}
