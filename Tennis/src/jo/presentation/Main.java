package jo.presentation;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

import jo.DAO.*;
import jo.business.*;

public class Main {

	public static void main(String[] args) {		
		// TODO Auto-generated method stub
		
		// CREATION DES ENREGISTREMENTS
		/*DAO<Joueur> joueurDAO = new JoueurDAO(TennisConnection.getInstance());
		for(int i=1; i<=128; i++) {
			if(!joueurDAO.create(new Joueur("player"+i, "m", i))) break ;
			
		}
		for(int i=129; i<=256; i++) {
			if(!joueurDAO.create(new Joueur("player"+i, "f", i-128))) break ;
			
		}*/
		
		
		/*DAO<Court> courtDAO = new CourtDAO(TennisConnection.getInstance());
		for(int i = 1; i < 6; i++){
			Court court = courtDAO.find(i);
			System.out.println("Court N°" + court.getId() + " - " + court.getNomCourt());
		}
		System.out.println("\n*****************************\n");
		
		ArrayList<Court> ac = courtDAO.getListObjects();
		for(Court c : ac) {
			System.out.println("Court N°" + c.getId() + " - " + c.getNomCourt());
		}
		System.out.println("\n*****************************\n");
		
		DAO<Joueur> joueurDAO = new JoueurDAO(TennisConnection.getInstance());
		for(int i = 1; i < 10; i++){
			Joueur joueur = joueurDAO.find(i);
			System.out.println("Joueur N°" + joueur.getId() + " - " + joueur.getNom() + " - " + joueur.getSexe() + " - " + joueur.getClassement());
		}
		
		
		System.out.println("\n*****************************\n");
		
		
		ArrayList<Joueur> aj = joueurDAO.getListObjects();
		for(Joueur j : aj) {
			System.out.println("Joueur N°" + j.getId() + " - " + j.getNom() + " - " + j.getSexe()  + " - " + j.getClassement());
		}
		
		System.out.println("\n*****************************\n");
		
		DAO<Arbitre> arbitreDAO = new ArbitreDAO(TennisConnection.getInstance());
		for(int i = 257; i <= 264; i++){
			Arbitre arbitre = arbitreDAO.find(i);
			System.out.println("Arbitre N°" + arbitre.getId() + " - " + arbitre.getNom() + " - " + arbitre.getSexe());
		}
		
		System.out.println("\n*****************************\n");
		
		
		ArrayList<Arbitre> aa = arbitreDAO.getListObjects();
		for(Arbitre a : aa) {
			System.out.println("Arbitre N°" + a.getId() + " - " + a.getNom() + " - " + a.getSexe());
		}
		
		
		System.out.println("\n***************************************************************************************\n");
		
		Ordonnancement o = new Ordonnancement("messieurs", 5);
		o.creationEquipes();
		for(Equipe e : o.getAe()) {
			for(Joueur j : e.getA()) {
				System.out.println("Joueur N°" + j.getId() + " - " + j.getNom() + " - " + j.getSexe()  + " - " + j.getClassement());
			}
		}
		
		System.out.println("\n***************************************************************************************\n");
		
		
		Ordonnancement o2 = new Ordonnancement("dames", 3);
		o2.creationEquipes();
		for(Equipe e : o2.getAe()) {
			for(Joueur j : e.getA()) {
				System.out.println("Joueur N°" + j.getId() + " - " + j.getNom() + " - " + j.getSexe()  + " - " + j.getClassement());
			}
		}
		
		System.out.println("\n***************************************************************************************\n");
		
		Ordonnancement o3 = new Ordonnancement("double", 5);
		o3.creationEquipes();
		for(Equipe e : o3.getAe()) {
			for(Joueur j : e.getA()) {
				System.out.println("Joueur N°" + j.getId() + " - " + j.getNom() + " - " + j.getSexe()  + " - " + j.getClassement());
			}
			System.out.println("--------------------------------------");
		}
		
		System.out.println("\n***************************************************************************************\n");
		
		Match m = new Match(LocalDateTime.now(),1, ac.get(0),3, o.getAe().get(0), o.getAe().get(1));
		m.playMatch();
		System.out.println(m.getResultat()[0]+"/" + m.getResultat()[1]);*/
		
		Ordonnancement o = new Ordonnancement("double",2, LocalDateTime.of(2020, 5, 18, 8, 0));  // debut du tournoi le 18 mai 2020 !
		o.creationEquipes();
		o.startBracket();
		int i=1;
		for(Match m : o.getAm()) {
			System.out.println(i++ + ")  "  + m.getDate() + " | " + m.getDuree() + " | " + m.getCourt().getId());
		}
	}
	
	

}
