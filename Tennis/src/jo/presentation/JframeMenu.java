package jo.presentation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import jo.business.Equipe;
import jo.business.Joueur;
import jo.business.Match;
import jo.business.Ordonnancement;
import jo.business.Tournoi;

import javax.swing.JTabbedPane;

public class JframeMenu extends JFrame {

	private JPanel contentPane;
	Tournoi t;
	//private ArrayList<ArrayList<ArrayList<Match>>> aTours= new ArrayList<ArrayList<ArrayList<Match>>> ();  
	// pr generer les brackets il  faut 5 tableaux pr chaque ordonnancement, 
	// dans chacun de ces tableaux il faut autant de tableaux pour le nombre de tours de chaque ordannancement
	// dans chacun de ces tableaux setrouvera une liste de match correspondant aux tours
	// -> on a donc 5 tableaux (ordonnancement) de tableaux ( tours) de tableaux (matchs)
	
	private ArrayList<JLabel> aJLabel = new ArrayList<JLabel>();
	ArrayList<ArrayList<ArrayList<JPanel>>> aJPanelNiveau3 = new ArrayList<ArrayList<ArrayList<JPanel>>>(); // Panneaux ou on va inserer les labels pr creer brackets
	ArrayList<ArrayList<JPanel>> aJPanelNiveau2 = new ArrayList<ArrayList<JPanel>>(); // besoin du dernier panel pour le top 16
	int x=10;
	int y=3;
	private int w=80;
	private int h=25;
	private int compteur=0;
	

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public JframeMenu(JframeAcceuil frameAcceuil, Tournoi t) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setVisible(true);
		
		this.t=t;
		drawOnglets();
		drawBrackets();
		

				
}
	private void drawOnglets() {
		//  creation conteneur principal
		JTabbedPane tabbedPane0 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane0.setBounds(12, 76, 958, 564);
		contentPane.add(tabbedPane0);
			
		// creation onglets de 1er niveau : ordonnancement (5)
		String[] titreOrdonnancement= { "Messieurs", "Dames", "Double Mrs", "Double Mme","Double Mixte" };
		ArrayList<JPanel> aJPnl= new ArrayList<JPanel>();
		for(int i = 0 ; i<5; i++) {
			aJPnl.add(new JPanel());
			aJPnl.get(i).setLayout(null);
			
			tabbedPane0.addTab(titreOrdonnancement[i], null, aJPnl.get(i), null);
		}
		// creation conteneurs pour chaque onglet d'ordonnancement (5)
		ArrayList<JTabbedPane> aJTab= new ArrayList<JTabbedPane>();
		for(int i = 0 ; i<5; i++) {
			aJTab.add(new JTabbedPane(JTabbedPane.TOP));
			aJTab.get(i).setBounds(12, 13, 929, 508);
			aJPnl.get(i).add(aJTab.get(i)); 
		}
	
		// creation onglets de 2eme niveau
		
		// simples
		String[] titreToursSimple= { "Tour 1", "Tour 2", "Tour 3", "Top 16"};
		for(int i = 0 ; i<2; i++) {
			aJPanelNiveau2.add(new ArrayList<JPanel>());
			for(int j=0; j<4; j++) { 
				aJPanelNiveau2.get(i).add(new JPanel());
				aJPanelNiveau2.get(i).get(j).setLayout(null);
				aJTab.get(i).addTab(titreToursSimple[j], null, aJPanelNiveau2.get(i).get(j), null);
			}			
		}
		// doubles
		String[] titreToursDouble= { "Tour 1", "Tour 2", "Top 16"};
		for(int i = 2 ; i<5; i++) {
			aJPanelNiveau2.add(new ArrayList<JPanel>());
			for(int j=0; j<3; j++) { 
				aJPanelNiveau2.get(i).add(new JPanel());
				aJPanelNiveau2.get(i).get(j).setLayout(null);
				aJTab.get(i).addTab(titreToursDouble[j], null, aJPanelNiveau2.get(i).get(j), null);
			}			
		}
				
		// creation conteneur pour chaque onglet de 2eme niveau 
		ArrayList<ArrayList<JTabbedPane>> aJTabbedPaneNiveau2 = new ArrayList<ArrayList<JTabbedPane>>();
		
		for(int i = 0 ; i<2; i++) {
			aJTabbedPaneNiveau2.add(new ArrayList<JTabbedPane>());
			for(int j=0; j<3; j++) { 
				aJTabbedPaneNiveau2.get(i).add(new JTabbedPane(JTabbedPane.TOP));
				aJTabbedPaneNiveau2.get(i).get(j).setBounds(12, 13, 900, 452);
				aJPanelNiveau2.get(i).get(j).add(aJTabbedPaneNiveau2.get(i).get(j));
			}			
		}
		for(int i = 2 ; i<5; i++) {
			aJTabbedPaneNiveau2.add(new ArrayList<JTabbedPane>());
			for(int j=0; j<2; j++) { 
				aJTabbedPaneNiveau2.get(i).add(new JTabbedPane(JTabbedPane.TOP));
				aJTabbedPaneNiveau2.get(i).get(j).setBounds(12, 13, 900, 452);
				aJPanelNiveau2.get(i).get(j).add(aJTabbedPaneNiveau2.get(i).get(j));
			}			
		}
			
		// creation onglets de 3eme niveau
		
		String[] titreGroupesSimple= { "groupe A", "groupe B", "groupe C", "groupe D", "groupe F", "groupe G", "groupe H", "groupe I"};
		
		// simple
		for(int i = 0 ; i<2; i++) {
			aJPanelNiveau3.add(new ArrayList<ArrayList<JPanel>>());
			for(int j=0; j<4; j++) {
				aJPanelNiveau3.get(i).add(new ArrayList<JPanel>());
			}
			// tour 1
			for(int k=0; k<8; k++) {
				aJPanelNiveau3.get(i).get(0).add(new JPanel());
				aJPanelNiveau3.get(i).get(0).get(k).setLayout(null);
				aJTabbedPaneNiveau2.get(i).get(0).addTab(titreGroupesSimple[k], null, aJPanelNiveau3.get(i).get(0).get(k), null);				
			}
			// tour 2
			for(int k=0; k<4; k++) {
				aJPanelNiveau3.get(i).get(1).add(new JPanel());
				aJPanelNiveau3.get(i).get(1).get(k).setLayout(null);
				aJTabbedPaneNiveau2.get(i).get(1).addTab(titreGroupesSimple[k], null, aJPanelNiveau3.get(i).get(1).get(k), null);				
			}
			// tour 3
			for(int k=0; k<2; k++) {
				aJPanelNiveau3.get(i).get(2).add(new JPanel());
				aJPanelNiveau3.get(i).get(2).get(k).setLayout(null);
				aJTabbedPaneNiveau2.get(i).get(2).addTab(titreGroupesSimple[k], null, aJPanelNiveau3.get(i).get(2).get(k), null);				
			}
		}
		// double
				for(int i = 2 ; i<5; i++) {
					aJPanelNiveau3.add(new ArrayList<ArrayList<JPanel>>());
					for(int j=0; j<3; j++) {
						aJPanelNiveau3.get(i).add(new ArrayList<JPanel>());
					}
					// tour 1
					for(int k=0; k<4; k++) {
						aJPanelNiveau3.get(i).get(0).add(new JPanel());
						aJPanelNiveau3.get(i).get(0).get(k).setLayout(null);
						aJTabbedPaneNiveau2.get(i).get(0).addTab(titreGroupesSimple[k], null, aJPanelNiveau3.get(i).get(0).get(k), null);				
					}
					// tour 2
					for(int k=0; k<2; k++) {
						aJPanelNiveau3.get(i).get(1).add(new JPanel());
						aJPanelNiveau3.get(i).get(1).get(k).setLayout(null);
						aJTabbedPaneNiveau2.get(i).get(1).addTab(titreGroupesSimple[k], null, aJPanelNiveau3.get(i).get(1).get(k), null);				
					}
				}
	}
			
	private void drawBrackets() {
		int compteurO=0; // ordonnancement
		int compteurT=1; // tour
		int compteurG=0; // compteur de groupe
		int compteurM=0; // compteur matchs par ordonnancement
		int saut=6;
		
		// simple
		
		
		//for(Ordonnancement o : t.getAo()) {
			for(Match m : t.getAo().get(0).getAm()) {
				compteurM++;
				System.out.println(compteurM +  ")  "  + m.getDate() + " | " + m.getDuree() + " | " + m.getCourt().getId() + " | tour n� " + m.getTour());
				if(m.getTour()>compteurT) {
					compteurT++;
					compteurG=0;
					if(compteurT<5) x+=150;
				}
				System.out.println("compteurO : " + compteurO);
				System.out.println("compteurT : " + compteurT);
				System.out.println("compteurG : " + compteurG);
				if(compteurT < 5) drawTour(m, aJPanelNiveau3.get(0).get(0).get(compteurG)); 
				else drawTour(m, aJPanelNiveau2.get(0).get(3)); // top 16 sur panel de niveau 2 !
				System.out.println(compteur);
				
				if(compteurT==1) {
					if(compteurM%8==0) {
						compteurG++;
						y=30;
					}
				}
				else if(compteurT==2) {
					if(compteurM%4==0) {
						compteurG++;
						y=30;
					}
					saut=45;
				}
				else if(compteurT==3) {
					if(compteurM%2==0) {
						compteurG++;
						y=0;
					}
					saut=120;		
				}
				else if(compteurT==4) {
					compteurG++;
					y=140;
					saut=50;
				}
				
				else if(compteurT==5) {
					if(compteurM%4==0) y=0;
 					x=10;
					saut=20;
				}
				else if(compteurT==6) {
					if(compteurM%4==0) y=0;
					x=150;
					saut=60;
					
				}
				else if(compteurT==7) {
					y = 0;
					x=200;
					saut=100;
				}
				y+=saut;
			//}
			compteurO++;
			compteurT=0;	
		}
		/*for(int i=0; i<2; i++) { // ordonnancements
			for(int j=0; j<t.getAo().get(i).getAm().size(); j++) { // matchs
				if(t.getAo().get(i).getAm().get(j).getTour()>compteurT) {
					compteurT++;
					compteurG=0;
					if(compteurT>4)compteurT=4;
				}	
				
				if((compteur+1)%8==0) compteurG++;
				System.out.println(compteur);
					
	
				drawTour(t.getAo().get(i).getAm().get(j),aJPanelNiveau3.get(i).get(compteurT-1).get(compteurG), x, y);
				
			}
			compteur=0;
			y=10;
			x=10;
		}*/
		
	}
	
	/*private void drawTour(List<Match> lm,JPanel jp, int x, int y, int intervale){	
		for(Match m :lm) {			
			for(Equipe e : m.getEquipes()) {
				if(e.getA().size()==1) {
					aJLabel.add(new JLabel(e.getA().get(0).getNom()));
					aJLabel.get(compteur).setBounds(x, y, w, h);					
				}
				else {
					aJLabel.add(new JLabel(e.getA().get(0).getNom() + " / " + e.getA().get(1).getNom()));
					aJLabel.get(compteur).setBounds(x, y, w+80, h);
				}
				
				aJLabel.get(compteur).setBorder(new LineBorder(new Color(0, 0, 0)));
				jp.add(aJLabel.get(compteur));
				compteur++;	
				y+=25;	
			}	
			y+=intervale;
		}
	}*/
	
	private void drawTour(Match m, JPanel jp){	
		for(Equipe e : m.getEquipes()) {
			if(e.getA().size()==1) {
				aJLabel.add(new JLabel(e.getA().get(0).getNom()));
				aJLabel.get(compteur).setBounds(x, y, w, h);					
			}
			else {
				aJLabel.add(new JLabel(e.getA().get(0).getNom() + " / " + e.getA().get(1).getNom()));
				aJLabel.get(compteur).setBounds(x, y, w+80, h);
			}
			aJLabel.get(compteur).setSize(70, 20);
			aJLabel.get(compteur).setBorder(new LineBorder(new Color(0, 0, 0)));
			jp.add(aJLabel.get(compteur));
			compteur++;	
			y+=20;	
		}
	}
	
}
