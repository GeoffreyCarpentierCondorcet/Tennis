package jo.presentation;
import java.sql.Connection;

import jo.business.*;
import jo.pojo.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DAO<Court> courtDAO = new CourtDAO(TennisConnection.getInstance());
		for(int i = 1; i < 6; i++){
			Court court = courtDAO.find(i);
			System.out.println("Court N°" + court.getId() + " - " + court.getNomCourt());
		}
		System.out.println("\n*****************************\n");

	}

}
