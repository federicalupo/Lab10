package it.polito.tdp.bar.model;

import java.time.LocalDateTime;
import java.time.LocalTime;


public class Event implements Comparable<Event> {

	public enum EventType{
		ARRIVO_GRUPPO_CLIENTI, TAVOLO_LIBERATO
	}
	
	//private LocalDateTime time;  //LOCALDATETIME PERCHè DEVO CONSIDERARE ANCHE GIORNO
	private LocalTime time;
	private  EventType eventType;
	private int nPersone;
	private float tolleranza; //variabile
	private int chiaveTavoloLiberato;

/*
	public Event(LocalDateTime time, EventType eventype, int nPersone, float tolleranza) {
		super();
		this.time = time;
		this.eventype = eventype;
		this.nPersone = nPersone;
		
		this.tolleranza = tolleranza;
	}

	/**COSTRUTTORE TAVOLO_LIBERATO => quando non metto ora apertura/chiusura
	 * 
	 * @param time
	 * @param eventype
	 * @param chiaveTavoloLiberato => in modo da aggiornare numero tavoli con quel nPosti
	 
	public Event(LocalDateTime time, EventType eventype, Integer chiaveTavoloLiberato) {
		super();
		this.time = time;
		this.eventype = eventype;
		this.chiaveTavoloLiberato= chiaveTavoloLiberato;
		
	}
*/	
	public Event(LocalTime time, EventType eventType, int nPersone, float tolleranza) {
		this.time= time;
		this.eventType = eventType;
		this.nPersone = nPersone;
		this.tolleranza = tolleranza;
		
	}

	public Event(LocalTime time, EventType eventype, Integer chiaveTavoloLiberato) {
		super();
		this.time = time;
		this.eventType = eventype;
		this.chiaveTavoloLiberato= chiaveTavoloLiberato;
		
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public EventType getEventype() {
		return eventType;
	}

	public void setEventype(EventType eventype) {
		this.eventType = eventype;
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
	@Override
	public String toString() {
		return "Event [time=" + time + ", eventype=" + eventType + ", nPersone=" + nPersone + ", tolleranza="
				+ tolleranza + "]";
	}
	
	
	
}
