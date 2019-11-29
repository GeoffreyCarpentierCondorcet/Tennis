package jo.presentation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import jo.business.Equipe;
import jo.business.Match;

public class JframeInfoMatch extends JFrame {
	private Match m;
	private JPanel contentPane;
	private ArrayList<JLabel> aJLabel = new ArrayList<JLabel>();
	int compteur= 0; // pr la creation des jlabels
	int positionxLabelPlayer;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public JframeInfoMatch(Match m) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(500, 350, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setLayout(null);
		setVisible(true);
		this.m = m;	
		drawArrayJeux();
		drawArrayTieBreak();
		drawInfos();
	}
	private void drawInfos() {
		JLabel tour = new JLabel("Tour : " + m.getTour());
		tour.setBounds(20, 10, 300, 30);
		tour.setFont(new Font("Verdana", Font.PLAIN, 20));
		contentPane.add(tour);
		JLabel debut = new JLabel("Debut : " + m.getDate().format(DateTimeFormatter.ofPattern("dd/MM/YYYY, HH:mm")));
		debut.setBounds(20, 37, 300, 30);
		debut.setFont(new Font("Verdana", Font.PLAIN, 20));
		contentPane.add(debut);
		LocalDateTime date=LocalDateTime.of(2020, 1, 1, 0, 0);
		JLabel duree = new JLabel("Duree : " + date.plusSeconds(m.getDuree()).format(DateTimeFormatter.ofPattern("HH:mm")));
		duree.setBounds(20, 65, 300, 30);
		duree.setFont(new Font("Verdana", Font.PLAIN, 20));
		contentPane.add(duree);
		JLabel court = new JLabel("Court : " + m.getCourt().getNomCourt(), SwingConstants.RIGHT);
		court.setBounds(260, 10, 300, 30);
		court.setFont(new Font("Verdana", Font.PLAIN, 20));
		contentPane.add(court);
		JLabel arbitre = new JLabel("arbitre : " + m.getArbitre().getNom(), SwingConstants.RIGHT);
		arbitre.setBounds(260, 37, 300, 30);
		arbitre.setFont(new Font("Verdana", Font.PLAIN, 20));
		contentPane.add(arbitre);
		
	}

	private void drawArrayJeux() {
		int[][] a = m.getResultatJeux();
		int x=0;
		int y=150;
		int decaleur=1; // decale vers le dessus ou le dessous pr comparer les resultats
		for(int i=0 ; i<2; i++) {
			for(int j=0; j<a[i].length; j++) {
				if(!(a[i][j]==0 && a[i+decaleur][j]==0)) {
					aJLabel.add(new JLabel(a[i][j]+"", SwingConstants.CENTER));
					aJLabel.get(compteur).setBounds(x+=40, y, 40, 40);
					aJLabel.get(compteur).setOpaque(true);
					aJLabel.get(compteur).setBackground(Color.WHITE);
					aJLabel.get(compteur).setBorder(new LineBorder(new Color(0, 0, 0)));
					contentPane.add(aJLabel.get(compteur++));	
				}			
			}
			// inscription des labels des joueurs
		
			if(m.getEquipes()[i].getA().size()==1) {
				aJLabel.add(new JLabel(m.getEquipes()[i].getA().get(0).getNom()));				
			}
			else {
				aJLabel.add(new JLabel(m.getEquipes()[i].getA().get(0).getNom() + " / " + m.getEquipes()[i].getA().get(1).getNom()));
			}
			aJLabel.get(compteur).setBounds(x+50, y, 300 , 40);
			aJLabel.get(compteur).setFont(new Font("Verdana", Font.BOLD, 20));
			contentPane.add(aJLabel.get(compteur++));	
			positionxLabelPlayer=x+50;
			y+=40;
			x=0;
			decaleur*=-1;
		}
	}
	private void drawArrayTieBreak() {
		int[][] a = m.getResultatTieBreaks();
		int x=40;
		int y=250;
		int decaleur=1;
		boolean flag=false;
		for(int i=0 ; i<2; i++) {
			for(int j=0; j<a[i].length; j++) {
				if(!(a[i][j]==0 && a[i+decaleur][j]==0)) {
					aJLabel.add(new JLabel(a[i][j]+"", SwingConstants.CENTER));
					aJLabel.get(compteur).setBounds(x, y, 40, 40);
					aJLabel.get(compteur).setOpaque(true);
					aJLabel.get(compteur).setBackground(Color.CYAN);
					aJLabel.get(compteur).setBorder(new LineBorder(new Color(0, 0, 0)));
					contentPane.add(aJLabel.get(compteur++));	
					flag=true;
				}
				x+=40;
			}
			y+=40;
			x=40;
			decaleur*=-1;
		}
		if(flag) {
			JLabel TieBreakLabel = new JLabel("Tie Break");
			TieBreakLabel.setBounds(positionxLabelPlayer, 260, 400, 80);
			TieBreakLabel.setForeground(Color.CYAN);
			TieBreakLabel.setFont(new Font("Verdana", Font.BOLD, 35));
			contentPane.add(TieBreakLabel);
		}
		
	}
	
}
