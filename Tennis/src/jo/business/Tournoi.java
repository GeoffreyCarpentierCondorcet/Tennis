package jo.business;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Tournoi {
	private ArrayList<Ordonnancement> ao = new ArrayList<Ordonnancement>();
	
	public Tournoi(LocalDateTime date) {	
		ao.add(new Ordonnancement("messieurs",3, date));
		ao.add(new Ordonnancement("dammes",2, date.plusDays(10)));
		ao.add(new Ordonnancement("double",2, date.plusDays(10)));		
	}
	
	public void startAllBrackets() {
		int i=1;
		for(Ordonnancement o : ao) {
			o.startBracket();
			
			for(Match m : o.getAm()) {
				System.out.println(i++ + ")  "  + m.getDate() + " | " + m.getDuree() + " | " + m.getCourt().getId());
			}
			i=1;			
		}
	}
}
