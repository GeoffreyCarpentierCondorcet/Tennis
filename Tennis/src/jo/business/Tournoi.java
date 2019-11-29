package jo.business;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Tournoi {
	private ArrayList<Ordonnancement> ao = new ArrayList<Ordonnancement>();
	
	public Tournoi(LocalDateTime date) {	
		ao.add(new Ordonnancement("messieurs",3, date));
		ao.add(new Ordonnancement("dames",2, date.plusDays(10)));
		ao.add(new Ordonnancement("doubleMessieurs",2, date.plusDays(10)));	
		ao.add(new Ordonnancement("doubleDames",2, date.plusDays(10)));	
		ao.add(new Ordonnancement("doubleMixte",2, date.plusDays(10)));				
	}
	
	public ArrayList<Ordonnancement> getAo() {
		return ao;
	}
	
	public void startAllBrackets() {
		for(Ordonnancement o : ao) {
			o.startBracket();			
		}
	}
}
