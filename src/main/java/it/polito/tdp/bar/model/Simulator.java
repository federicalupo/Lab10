package it.polito.tdp.bar.model;

import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

import it.polito.tdp.bar.model.Event.EventType;

public class Simulator {

	private PriorityQueue<Event> queue = new PriorityQueue<>();
	
	private int totTavoli = 15;
	private Map<Integer, Integer> tavoli ; //<nposti, ntavoli>
	private boolean trovato;
	private float tolleranzaCostante; //da impostare
	
	//tavoli da settare??? dubbiooooo
	
	private int nTotClienti;
	private int nClientiSoddisfatti;
	private int nClientiInsoddisfatti;
	
	public int getnTotClienti() {
		return nTotClienti;
	}
	public int getnClientiSoddisfatti() {
		return nClientiSoddisfatti;
	}
	public int getnClientiInsoddisfatti() {
		return nClientiInsoddisfatti;
	}
	
	public void run() {
		tavoli = new TreeMap<>();
		
		this.tolleranzaCostante =(float)0.7;
		tavoli.put(4, 5);
		tavoli.put(6, 4);
		tavoli.put(8, 4);
		tavoli.put(10, 2);
	
		totTavoli = 15;
		 
		 nTotClienti = 0;
		 nClientiSoddisfatti = 0;
		 nClientiInsoddisfatti = 0;
		 
		//devo stabilire comunque una partenza?  dubbioooooo
		this.queue.clear(); //pulire struttura dati
		
		LocalTime oraArrivo = LocalTime.now();
		
		for(int i = 0; i<2000; i++) {
			
			oraArrivo = oraArrivo.plus(Duration.of(((long)(Math.random()*10)+1), ChronoUnit.MINUTES));
			int nPersone = (int)((Math.random()*10)+1);
			float tolleranza = (float)Math.random();
			
			Event e = new Event(oraArrivo, EventType.ARRIVO_GRUPPO_CLIENTI, nPersone, tolleranza);
			queue.add(e);
		}
		
		
		while( ! queue.isEmpty()) {
			Event e = queue.poll();
			processEvent(e);
		}
		
		
		
		
		
	}
	private void processEvent(Event e) {
		
		switch(e.getEventype()) {
			case ARRIVO_GRUPPO_CLIENTI:
				trovato = false; // devo resettarlo per ogni evento arrivo_gruppo_clienti
				
				if(this.totTavoli>0) { //c'è generico tavolo?
					
					for(Integer nPosti : tavoli.keySet()) { //sono in ordine
						if(nPosti>=e.getnPersone() && (e.getnPersone()>=(0.5)*nPosti) && !trovato && tavoli.get(nPosti)>0) { 
							/** si siedono se:
							 * numero posti >= numero persone
							 * numero persone >= metà posti disponibili
							 * non è stato trovato un tavolo
							 * se quel preciso tavolo è presente
							 * 
							 */
							this.nTotClienti++;
							this.nClientiSoddisfatti++;
							this.totTavoli--;
							tavoli.replace(nPosti, tavoli.get(nPosti)-1); //riduce il numero di tavoli con quel nPosti
							trovato = true;
							
							//oraArrivo + permanenza 
							//dubbioooo?? tra 60 e 120?
							
							
							LocalTime tempoPermanenza = e.getTime().plus(Duration.of((long)((Math.random()*60)+60), ChronoUnit.MINUTES));
							Event evento = new Event(tempoPermanenza, EventType.TAVOLO_LIBERATO, nPosti);
							this.queue.add(evento);
						}
					}
					
					
					if(!trovato) { //se non è disponibile tavolo della capienza giusta, puoi andare al bancone
						
						if(e.getTolleranza() >= this.tolleranzaCostante) { //bancone => capienza illimitata
							this.nTotClienti++;
							this.nClientiSoddisfatti++;
						}else {
							this.nTotClienti++;
							this.nClientiInsoddisfatti++;
						}
					}
					
					
				}else {
					//non c'è tavolo, vuoi sederti al bancone?
					if(e.getTolleranza() >= this.tolleranzaCostante) {
						this.nTotClienti++;
						this.nClientiSoddisfatti++;
					}else {
						this.nTotClienti++;
						this.nClientiInsoddisfatti++;
					}
					
				}
				
				
				
				break;
			case TAVOLO_LIBERATO:
				
				this.totTavoli++;
				tavoli.replace(e.getChiaveTavoloLiberato(), tavoli.get(e.getChiaveTavoloLiberato())+1);
		
				
			break;
		}
		
		
	}
	
	
	
	
	
	
	
}
