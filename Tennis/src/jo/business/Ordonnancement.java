package jo.business;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import jo.DAO.DAO;
import jo.DAO.JoueurDAO;
import jo.DAO.TennisConnection;

public class Ordonnancement {
	ArrayList<Joueur> aj;
	ArrayList<Equipe> ae = new ArrayList<Equipe>();
	String type;
	int nbrSetsGagnants;
	
	public Ordonnancement(String type, int nbrSetsGagnants) {
		this.type = type;
		this.nbrSetsGagnants = nbrSetsGagnants;
	}
	
	public ArrayList<Equipe> getAe() {
		return ae;
	}
	
	public ArrayList<Joueur> getAj() {
		return aj;
	}
	
	public void getListJoueurs() {
		DAO<Joueur> joueurDAO = new JoueurDAO(TennisConnection.getInstance());
		aj = joueurDAO.getListObjects();		
	}
	public void creationEquipes() {
		Collections.shuffle(aj); // m�lange al�atoire de la liste des joueurs
		ArrayList<Joueur> a = new ArrayList<Joueur>();
			
		switch(type) {
		case "messieurs" : 
			
			for(Joueur j : aj) {
				if(j.getSexe().equals("m")) a.add(j);
			}
			for(Joueur j : a) { // Attribution des �quipes dans le tableau ae
				ae.add(new Equipe(j));
			}
			break;
		case "dames" : 			
			for(Joueur j : aj) {
				if(j.getSexe().equals("f")) a.add(j);
			}
			for(Joueur j : a) { // Attribution des �quipes dans le tableau ae
				ae.add(new Equipe(j));
			}
			break;
		case "double" : 
			ArrayList<Joueur> a2 = new ArrayList<Joueur>();
			List<Joueur> m;
			List<Joueur> f;
			for(Joueur j : aj) {
				if(j.getSexe().equals("m"))a.add(j);
				else a2.add(j);
				
			}
			m = a.subList(0, 63);
			f = a2.subList(0, 63);
			for(int i=0; i<m.size();i++) { // Attribution des �quipes dans le tableau ae
				ae.add(new Equipe(m.get(i),f.get(i)));
			}
			break;
		}
		
	}
}
