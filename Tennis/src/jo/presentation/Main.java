package jo.presentation;
import java.sql.Connection;
import java.util.ArrayList;

import jo.DAO.*;
import jo.business.*;

public class Main {

	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		DAO<Court> courtDAO = new CourtDAO(TennisConnection.getInstance());
		for(int i = 1; i < 6; i++){
			Court court = courtDAO.find(i);
			System.out.println("Court N�" + court.getId() + " - " + court.getNomCourt());
		}
		System.out.println("\n*****************************\n");
		
		ArrayList<Court> ac = courtDAO.getListObjects();
		for(Court c : ac) {
			System.out.println("Court N�" + c.getId() + " - " + c.getNomCourt());
		}
		System.out.println("\n*****************************\n");
		
		DAO<Joueur> joueurDAO = new JoueurDAO(TennisConnection.getInstance());
		for(int i = 1; i < 10; i++){
			Joueur joueur = joueurDAO.find(i);
			System.out.println("Joueur N�" + joueur.getId() + " - " + joueur.getNom() + " - " + joueur.getSexe() + " - " + joueur.getClassement());
		}
		
		System.out.println("\n*****************************\n");
		
		
		ArrayList<Joueur> aj = joueurDAO.getListObjects();
		for(Joueur j : aj) {
			System.out.println("Joueur N�" + j.getId() + " - " + j.getNom() + " - " + j.getSexe()  + " - " + j.getClassement());
		}
		System.out.println("\n***************************************************************************************\n");
		
		Ordonnancement o = new Ordonnancement("messieurs", 5);
		o.getListJoueurs();
		o.creationEquipes();
		for(Equipe e : o.getAe()) {
			for(Joueur j : e.getA()) {
				System.out.println("Joueur N�" + j.getId() + " - " + j.getNom() + " - " + j.getSexe()  + " - " + j.getClassement());
			}
		}
		
		System.out.println("\n***************************************************************************************\n");
		
		
		Ordonnancement o2 = new Ordonnancement("dames", 3);
		o2.getListJoueurs();
		o2.creationEquipes();
		for(Equipe e : o2.getAe()) {
			for(Joueur j : e.getA()) {
				System.out.println("Joueur N�" + j.getId() + " - " + j.getNom() + " - " + j.getSexe()  + " - " + j.getClassement());
			}
		}
		
		System.out.println("\n***************************************************************************************\n");
		
		Ordonnancement o3 = new Ordonnancement("double", 5);
		o3.getListJoueurs();
		o3.creationEquipes();
		for(Equipe e : o3.getAe()) {
			for(Joueur j : e.getA()) {
				System.out.println("Joueur N�" + j.getId() + " - " + j.getNom() + " - " + j.getSexe()  + " - " + j.getClassement());
			}
			System.out.println("--------------------------------------");
		}
		
		System.out.println("\n***************************************************************************************\n");
		// CREATION DES ENREGISTREMENTS
		/*DAO<Joueur> joueurDAO = new JoueurDAO(TennisConnection.getInstance());
		for(int i=1; i<=128; i++) {
			if(!joueurDAO.create(new Joueur("player"+i, "m", i))) break ;
			
		}
		for(int i=129; i<=256; i++) {
			if(!joueurDAO.create(new Joueur("player"+i, "f", i-128))) break ;
			
		}*/
	}
	
	

}
