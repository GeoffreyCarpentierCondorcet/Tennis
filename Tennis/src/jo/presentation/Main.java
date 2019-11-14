package jo.presentation;
import java.sql.Connection;
import java.util.ArrayList;

import jo.business.*;
import jo.pojo.*;

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
			System.out.println("Joueur N�" + joueur.getId() + " - " + joueur.getNom() + " - " + joueur.getSexe());
		}
		
		System.out.println("\n*****************************\n");
		
		
		ArrayList<Joueur> aj = joueurDAO.getListObjects();
		for(Joueur j : aj) {
			System.out.println("Joueur N�" + j.getId() + " - " + j.getNom() + " - " + j.getSexe());
		}
		System.out.println("\n*****************************\n");

	}

}