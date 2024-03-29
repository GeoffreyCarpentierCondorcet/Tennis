package jo.business;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

import jo.DAO.ArbitreDAO;
import jo.DAO.CourtDAO;
import jo.DAO.DAO;
import jo.DAO.JoueurDAO;
import jo.DAO.TennisConnection;

public class Ordonnancement {
	private ArrayList<Joueur> aj;
	private ArrayList<Arbitre> aa;
	private ArrayList<Court> ac;
	private ArrayList<Equipe> ae = new ArrayList<Equipe>();
	private ArrayList<Match> am = new ArrayList<Match>();
	private String type;
	private int nbrSetsGagnants;
	private LocalDateTime date;
	
	
	public Ordonnancement(String type, int nbrSetsGagnants, LocalDateTime date) {
		this.type = type;
		this.nbrSetsGagnants = nbrSetsGagnants;
		loadListJoueurs();
		loadListArbitres();
		loadListCourts();
		creationEquipes();
		this.date=date;
	}
	
	public ArrayList<Equipe> getAe() {
		return ae;
	}
	
	public ArrayList<Joueur> getAj() {
		return aj;
	}
	
	public ArrayList<Match> getAm() {
		return am;
	}
	
	private void loadListJoueurs() {
		DAO<Joueur> joueurDAO = new JoueurDAO(TennisConnection.getInstance());
		aj = joueurDAO.getListObjects();		
	}
	
	private void loadListArbitres() {
		DAO<Arbitre> arbitreDAO = new ArbitreDAO(TennisConnection.getInstance());
		aa = arbitreDAO.getListObjects();
	}
	
	private void loadListCourts() {
		DAO<Court> courtDAO = new CourtDAO(TennisConnection.getInstance());
		ac = courtDAO.getListObjects();
	}
	
	
	private void creationEquipes() {
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
		case "doubleMessieurs" : 
			for(Joueur j : aj) {
				if(j.getSexe().equals("m"))a.add(j);	
			}
			for(int i=0, j=64; i<64; i++, j++) {
				ae.add(new Equipe(a.get(i),a.get(j)));
			}
			break;
		case "doubleDames" : 
			for(Joueur j : aj) {
				if(j.getSexe().equals("f"))a.add(j);	
			}
			for(int i=0, j=64; i<64; i++, j++) {
				ae.add(new Equipe(a.get(i),a.get(j)));
			}
			break;
		case "doubleMixte" : 
			ArrayList<Joueur> a2 = new ArrayList<Joueur>();
			List<Joueur> m;
			List<Joueur> f;
			for(Joueur j : aj) {
				if(j.getSexe().equals("m"))a.add(j);
				else a2.add(j);
				
			}
			m = a.subList(0, 64); // tronquage � 64 hommes
			f = a2.subList(0, 64); // tronquage � 64 femmes
			for(int i=0; i<m.size();i++) { // Attribution des �quipes dans le tableau ae
				ae.add(new Equipe(m.get(i),f.get(i)));
			}
			break;
		}		
	}
	public void startBracket() {
		ListIterator<Equipe> bracket = ae.listIterator(); // on supprime au fur et � mesure les joueurs du bracket 
		int tour=1;
		int flag=0; // court et arbitre
		int compteur=0; // nbr matchs jou�s
		int limite;
		
		
		limite = (ae.size()>64)? 7:6; // nbr tours varie en fonction du nombre d'equipes !
		
		while(tour<=limite) {
			while(bracket.hasNext()) {
				am.add(new Match(date,tour, ac.get(flag), nbrSetsGagnants,aa.get(flag++),bracket.next(),bracket.next()));
				am.get(compteur).playMatch(); // lancement du match
				
				if(am.get(compteur).getResultatSets()[0]>am.get(compteur++).getResultatSets()[1]) { // si E1 gagne
					bracket.remove(); // E2 elumin�e
				}
				else { // si E2 gagne
					bracket.previous();
					bracket.remove(); // E1 elumin�e 	
				}
							
				if(flag==8) { // qd 8 matchs ont �t� jou�s 
					if((compteur)%24!=0) date=date.plusHours(4); // 3 matchs par court par jour � 8, 12, 16h -> 24 matchs par jours avant les 8eme de finale
					else date=date.plusHours(16); //qd 3 matchs joues sur le meme court par jour on rejoue le lendemain � 8h
					flag=0; 
				}
				
				/* 1/4 de finale
				 * ---------------------------*/
				else if((limite==6 && tour == 4) || limite==7 && tour ==5) { 
					// 4 matchs/jour -> 2 matchs � 8h du mat sur les courts 1, 2 et 2 matchs � 16h sur les courts 3, et 4
					if(flag==2) date=date.plusHours(8);
					else if(flag==4) {
						date=date.plusHours(16);
						flag=0;
					}
				}
				
				/* 1/2 finale
				 * ---------------------------*/
				else if((limite==6 && tour == 5) || limite==7 && tour ==6) { 
					// 2 matchs/jour -> 1 match � 8h du mat sur le court 1, et 1 autre � 16h sur le court 2
					date=date.plusHours(8);
					if(flag==2) {
						date=date.plusHours(14); // finale le landemain � 14h sur court 1
						flag=0;
					}
				}	
			}
			
			while(bracket.hasPrevious()) bracket.previous(); // revenir au debut de l'iterator
			tour++;	
		}	
	}
}
