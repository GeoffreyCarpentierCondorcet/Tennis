package jo.business;

import java.util.ArrayList;

public class Equipe {
	ArrayList<Joueur> a = new ArrayList<Joueur>();
	
	public Equipe(Joueur j) {
		a.add(j);
	}
	
	public Equipe(Joueur j1, Joueur j2) {
		a.add(j1);
		a.add(j2);
	}
	
	public ArrayList<Joueur> getA() {
		return a;
	}
}
