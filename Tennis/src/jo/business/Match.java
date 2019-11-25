package jo.business;


import java.time.LocalDateTime;
import java.util.Random;

public class Match {
	Equipe[] equipe = new Equipe[2];
	int[] resultat = new int[2];
	Court court;
	Arbitre arbitre;
	LocalDateTime date;
	int duree; // en seconde
	int tour;
	Random r = new Random();
	int nbrSetsGagnants;
	
	public Match(LocalDateTime date, int tour, Court court,int nbrSetsGagnants, Arbitre arbitre, Equipe e1, Equipe e2) {
		this.date = date;
		this.tour = tour;
		this.court = court;
		this.nbrSetsGagnants = nbrSetsGagnants;
		this.arbitre = arbitre;
		equipe[0] = e1;
		equipe[1] = e2;
	}
	
	public int[] getResultat() {
		return resultat;
	}	
	public int getDuree() {
		return duree;
	}
	public LocalDateTime getDate() {
		return date;
	}
	public Court getCourt() {
		return court;
	}
	
	/* attribution des points, jeux et set :  true -> equipe1 marque, false -> equipe2 marque */
	
	private boolean playPoint() { 
		duree+=r.nextInt(60-10 +1)+10; // 10 � 60 sec par points
		if(r.nextInt(2)==0) return true; //  equipe 1 marque le point
		else return false; //  equipe 2 marque le point

	}
	
	private boolean playJeu() {
		int pointsE1=0;
		int pointsE2=0;
		
		/* generation des points tant que c'est pas 40/40
		 * -------------------------------------------------*/
		while(!(pointsE1==40 && pointsE2==40)) { 
			if(playPoint()) pointsE1+=15;
			else pointsE2+=15;
			if(pointsE1==45) pointsE1=40;
			if(pointsE2==45) pointsE2=40;
			if(pointsE1>40) return true; // equipe 1 gagne le jeu
			if(pointsE2>40) return false; // equipe 2 gagne le jeu
		}
		
		/* si score � 40/40
		 * -------------------------------------------------*/
		
		int avantage=0; // 0-> egalite, 1-> avantage equipe 1, -1> avantage equipe 2
		
		while(avantage<2 && avantage>-2) {
			if(playPoint()) avantage++; // si equipe 1 marque
			else avantage--; // si equipe 2 marque
		}
		if(avantage==2) return true; // equipe 1 gagne le jeu
		else return false; // equipe 2 gagne le jeu
				
	}
	private boolean playSet() {
		int jeuxE1=0;
		int jeuxE2=0;
		
		/* generation des jeux tant que c'est pas 6/6
		 * -------------------------------------------------*/
		
		while(!(jeuxE1==6 && jeuxE2==6)) {
			if(playJeu()) jeuxE1++;
			else jeuxE2++;
			if(jeuxE1>6) return true; // equipe 1 gagne le set
			if(jeuxE2>6) return false; // // equipe 2 gagne le set
		}
				
		/* si score �  6/6 et pas dernier set -> tie-break
		 * -------------------------------------------------*/
		if(resultat[0]+resultat[1]<nbrSetsGagnants*2-2 ) {		
			int pointsE1=0;
			int pointsE2=0;

			/* generation points tant que pas de joueur � 7
			 * -------------------------------------------------*/
			while(pointsE1<=7 && pointsE2<=7) {
				if(playPoint()) pointsE1++;
				else pointsE2++;
			}
			
			if(pointsE1-pointsE2>=2) return true; // equipe 1 gagne le set
			else if(pointsE1-pointsE2<=-2) return false; // equipe 2 gagne le set
			
			
			/* si un joueur a 7, generation points tant que pas 2 points d'�cart
			 * --------------------------------------------------------------------*/
			else {
				while(pointsE1-pointsE2<2 && pointsE1-pointsE2>-2) {
					if(playPoint()) pointsE1++;
					else pointsE2++;
				}
				if(pointsE1>pointsE2) return true; // equipe 1 gagne le set
				else return false;// equipe 2 gagne le set	
			}
		}
		
		/* si score � 6/6 et dernier set -> 2 jeux d'ecart
		 * -------------------------------------------------*/
		else { 
			while(jeuxE1-jeuxE2<2 && jeuxE1-jeuxE2>-2) {
				if(playJeu()) jeuxE1++;
				else jeuxE2++;
			}
			if(jeuxE1>jeuxE2) return true; // equipe 1 gagne le set
			else return false;// equipe 2 gagne le set	
		}	
	}
	
	public void playMatch() {
		while(resultat[0]<nbrSetsGagnants && resultat[1]<nbrSetsGagnants) {
			if(playSet()) resultat[0]++;
			else resultat[1]++;
		}
		if (duree>13200) duree = 14400 ; // temps max par match : 3h40 -> 3 matchs/court par jour tt les 4 h j'usqu'en 1/8 eme de finale
	}
}
