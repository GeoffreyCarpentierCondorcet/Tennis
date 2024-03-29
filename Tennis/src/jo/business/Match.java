package jo.business;


import java.time.LocalDateTime;
import java.util.Random;

public class Match {
	Equipe[] equipe = new Equipe[2];
	int[] resultatSets = new int[2];
	int[][] resultatJeux = new int[2][];
	int[][] resultatTieBreak = new int[2][];
	Court court;
	Arbitre arbitre;
	LocalDateTime date;
	int duree; // en seconde
	int tour;
	Random r = new Random();
	int nbrSetsGagnants;
	int numSet;
	
	public Match(LocalDateTime date, int tour, Court court,int nbrSetsGagnants, Arbitre arbitre, Equipe e1, Equipe e2) {
		this.date = date;
		this.tour = tour;
		this.court = court;
		this.nbrSetsGagnants = nbrSetsGagnants;
		this.arbitre = arbitre;
		equipe[0] = e1;
		equipe[1] = e2;
		resultatJeux[0] = new int[nbrSetsGagnants+2];
		resultatJeux[1] = new int[nbrSetsGagnants+2];
		resultatTieBreak[0] = new int[nbrSetsGagnants+1]; // +1 car pas de tie break l dernier set
		resultatTieBreak[1] = new int[nbrSetsGagnants+1];
	}
	
	public int[] getResultatSets() {
		return resultatSets;
	}
	public int[][] getResultatJeux() {
		return resultatJeux;
	}
	public int[][] getResultatTieBreaks() {
		return resultatTieBreak;
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
	public Equipe[] getEquipes() {
		return equipe;
	}
	public int getTour() {
		return tour;
	}
	public Arbitre getArbitre() {
		return arbitre;
	}
	
	/* attribution des points, jeux et set :  true -> equipe1 marque, false -> equipe2 marque */
	
	private boolean playPoint() { 
		duree+=r.nextInt(50-10 +1)+10; // 10 � 50 sec par points
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
		
		/* generation des jeux tant qu'un joueur n'est pas � 6
		 * ------------------------------------------------------*/
		
		while(resultatJeux[0][numSet]<6 && resultatJeux[1][numSet]<6) {
			if(playJeu()) resultatJeux[0][numSet]++;
			else resultatJeux[1][numSet]++;
		}		
		/* qd 1des joueurs est � 6 est ce qu'il y'a 2 points d'�cart ?
		 * ---------------------------------------------------------------*/
		// si oui le set se termine, sinon �a continue
		
		if(resultatJeux[0][numSet]-resultatJeux[1][numSet]>=2) return true; // equipe 1 gagne le set
		else if(resultatJeux[0][numSet]-resultatJeux[1][numSet]<=-2) return false; // equipe 2 gagne le set		
		
		/* si score � 6/5 on rejoue
		 * -------------------------------------------------*/			
		if (playJeu()) resultatJeux[0][numSet]++;
		else resultatJeux[1][numSet]++;
		
		/* si 2 points d'ecart le set se termine
		 * -------------------------------------------------*/
		if(resultatJeux[0][numSet]-resultatJeux[1][numSet]>=2) return true; // equipe 1 gagne le set
		else if(resultatJeux[0][numSet]-resultatJeux[1][numSet]<=-2) return false; // equipe 2 gagne le set
		
		// sinon score � 6/6 ->
					
		/* si score �  6/6 et pas dernier set -> tie-break
		 * -------------------------------------------------*/
		if(resultatSets[0]+resultatSets[1]<(nbrSetsGagnants*2)-2 ) {
			if(playTieBreak()) {
				resultatJeux[0][numSet]++;
				return true;
				
				}
			else {
				resultatJeux[1][numSet]++;
				return false;
			}
		}
		
		/* si score � 6/6 et dernier set -> 2 jeux d'ecart
		 * -------------------------------------------------*/
		else { 
			while(resultatJeux[0][numSet]-resultatJeux[1][numSet]<2 && resultatJeux[0][numSet]-resultatJeux[1][numSet]>-2) {
				if(playJeu()) resultatJeux[0][numSet]++;
				else resultatJeux[1][numSet]++;
			}
			if(resultatJeux[0][numSet]>resultatJeux[1][numSet]) return true; // equipe 1 gagne le set
			else return false;// equipe 2 gagne le set	
		}	
	}
	
	private boolean playTieBreak() {

		/* generation points tant que pas de joueur � 7
		 * -------------------------------------------------*/
		while(resultatTieBreak[0][numSet]<7 && resultatTieBreak[1][numSet]<7) {
			if(playPoint()) resultatTieBreak[0][numSet]++;
			else resultatTieBreak[1][numSet]++;
		}
		
		if(resultatTieBreak[0][numSet]-resultatTieBreak[1][numSet]>=2) return true; // equipe 1 gagne le set
		else if(resultatTieBreak[0][numSet]-resultatTieBreak[1][numSet]<=-2) return false; // equipe 2 gagne le set
		
		
		/* si un joueur a 7, generation points tant que pas 2 points d'�cart
		 * --------------------------------------------------------------------*/
		else {
			while(resultatTieBreak[0][numSet]-resultatTieBreak[1][numSet]<2 && resultatTieBreak[0][numSet]-resultatTieBreak[1][numSet]>-2) {
				if(playPoint()) resultatTieBreak[0][numSet]++;
				else resultatTieBreak[1][numSet]++;
			}
			if(resultatTieBreak[0][numSet]>resultatTieBreak[1][numSet]) return true; // equipe 1 gagne le set
			else return false;// equipe 2 gagne le set	
		
		}
	}
	
	public void playMatch() {
		while(resultatSets[0]<nbrSetsGagnants && resultatSets[1]<nbrSetsGagnants) {
			if(playSet()) resultatSets[0]++;
			else resultatSets[1]++;
			numSet++;
		}
		if (duree>13200) duree = 13200 ; // temps max par match : 3h40 -> 3 matchs/court par jour tt les 4 h j'usqu'en 1/8 eme de finale
	}
}
