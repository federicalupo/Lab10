package it.polito.tdp.bar.model;

import java.time.Duration;
import java.time.LocalDateTime;

import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

import it.polito.tdp.bar.model.Event.EventType;

public class Simulator {

	private PriorityQueue<Event> queue = new PriorityQueue<>();
	
	private int totTavoli=0;
	private Map<Integer, Integer> tavoli = new TreeMap<>(); //<nposti, ntavoli>
	private boolean trovato;
	private final float TOLLERANZACOSTANTE = (float)0.7; //da impostare- si, rivedo
	

	
	private int nTotClienti;
	private int nClientiSoddisfatti;
	private int nClientiInsoddisfatti;
	
	//tavoli da settare
	public void aggiungiTavolo(Integer nPosti, Integer nTavoli) {
		
		totTavoli+=nTavoli;  
		// => per ogni persona che si siede è previsto che se ne vada dopo un pò=> quindi ogni tavolo si libera
		//funge da variabile di stato
		
		if(tavoli.containsKey(nPosti)) { //se c'è già aggiorna nTavoli
			tavoli.replace(nPosti, tavoli.get(nPosti)+nTavoli);
		}else {
			tavoli.put(nPosti, nTavoli);
		}
	}
	
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
		
		 nTotClienti = 0;
		 nClientiSoddisfatti = 0;
		 nClientiInsoddisfatti = 0;
		 
		 
		this.queue.clear(); //pulire struttura dati
		
		LocalDateTime oraArrivo = LocalDateTime.now(); //NO LOCALTIME !! PErCHè DUE GIORNI DIVERSI, CON STESSA ORA, VENGONO ORDINATI UNO DOPO L'ALTRO PERCHè SI GUARDA SOLO L'ORA
		
		for(int i = 0; i<30; i++) {
			
			oraArrivo = oraArrivo.plus(Duration.of(((int)(Math.random()*10)+1), ChronoUnit.MINUTES)); // cast in int
			
			int nPersone = (int)((Math.random()*10)+1);
			float tolleranza = (float)Math.random(); //1 è da includere?
			
			Event e = new Event(oraArrivo, EventType.ARRIVO_GRUPPO_CLIENTI, nPersone, tolleranza);
			 
			queue.add(e);
		}
		
		
		while( ! queue.isEmpty()) {
			Event e = queue.poll();
			//provo a stampare per debug
			//System.out.println(e); 
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
							//Random con estremi inclusi => (random * (max-min+1) )  +  min
							
							
							LocalDateTime tempoPermanenza = e.getTime().plus(Duration.of((int)( (Math.random()*61)  +60), ChronoUnit.MINUTES));
							Event evento = new Event(tempoPermanenza, EventType.TAVOLO_LIBERATO, nPosti);
							this.queue.add(evento);
						}
					}
					
					
					if(!trovato) { //se non è disponibile tavolo della capienza giusta, puoi andare al bancone
						
						if(e.getTolleranza() >= TOLLERANZACOSTANTE) { //bancone => capienza illimitata
							this.nTotClienti++;
							this.nClientiSoddisfatti++;
						}else {
							this.nTotClienti++;
							this.nClientiInsoddisfatti++;
						}
					}
					
					
				}else {
					//non c'è tavolo, vuoi sederti al bancone?
					if(e.getTolleranza() >= TOLLERANZACOSTANTE) {
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
