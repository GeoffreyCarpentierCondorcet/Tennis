package jo.presentation;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import jo.business.Equipe;
import jo.business.Joueur;
import jo.business.Match;
import jo.business.Ordonnancement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class JframeBracket extends JFrame {

	private JPanel contentPane;

	public JframeBracket(JframeAcceuil frameAcceuil, Ordonnancement o) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setVisible(true);		
		JframeBracket thisframe = this;
		
		
		JButton btn_groupeA = new JButton("groupe A");
		btn_groupeA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(o.getAm().size()>64) { // si 128 equipes 
					JframeGroupe frameA = new JframeGroupe(o.getAm().subList(0, 16), o.getAm().subList(64, 72),o.getAm().subList(96, 100), o.getAm().subList(112, 114), o.getAm().subList(120, 121)); 
				}
				else {JframeGroupe frameA = new JframeGroupe(o.getAm().subList(0, 16), o.getAm().subList(32, 40),o.getAm().subList(48, 52), o.getAm().subList(56, 58), o.getAm().subList(60, 61)); }
			}
		});
		JButton btn_groupeB = new JButton("groupe B");
		btn_groupeB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(o.getAm().size()>64) { // si 128 equipes 
					JframeGroupe frameB = new JframeGroupe(o.getAm().subList(16, 32), o.getAm().subList(72, 80), o.getAm().subList(100, 104), o.getAm().subList(114, 116), o.getAm().subList(121, 122));
					
				}
				else {JframeGroupe frameA = new JframeGroupe(o.getAm().subList(16, 32), o.getAm().subList(40, 48),o.getAm().subList(52, 56), o.getAm().subList(58, 60), o.getAm().subList(61, 62));}
			}
		});
		
		JButton btn_top = new JButton("top 4");
		if(o.getAm().size()<=64) btn_top.setText("finale");
		btn_top.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(o.getAm().size()>64) { // si 128 equipes
					 JframeGroupe f = new  JframeGroupe(o.getAm().subList(124, 126), o.getAm().subList(126, 127)); 
				}	
				else { // si 64 equipes
					 JframeGroupe f = new  JframeGroupe(o.getAm().subList(62, 63)); 
					
				}
			}
		});
			
		if(o.getAm().size()>64) {	// 2 groupes supplémentaires pour les tournois de 128 equipes
			JButton btn_groupeC = new JButton("groupe C");
			btn_groupeC.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					JframeGroupe frameC = new JframeGroupe(o.getAm().subList(32, 48), o.getAm().subList(80, 88), o.getAm().subList(104, 108), o.getAm().subList(116, 118), o.getAm().subList(122, 123)); 
				}
			});
			JButton btn_groupeD = new JButton("groupe D");
			btn_groupeD.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					JframeGroupe frameD = new JframeGroupe(o.getAm().subList(48, 64),o.getAm().subList(88, 96), o.getAm().subList(108, 112), o.getAm().subList(118, 120), o.getAm().subList(123, 124)); 
				}
			});
			
			btn_groupeC.setBounds(100, 200, 100, 25);
			btn_groupeD.setBounds(100, 250, 100, 25);
			contentPane.add(btn_groupeC);
			contentPane.add(btn_groupeD);
			
		}
		
		btn_groupeA.setBounds(100, 100, 100, 25);
		btn_groupeB.setBounds(100, 150, 100, 25);
		btn_top.setBounds(100, 400, 100, 25);
		contentPane.add(btn_groupeA);
		contentPane.add(btn_groupeB);
		contentPane.add(btn_top);

		JButton btn_retourAcceuil = new JButton("Acceuil");
		btn_retourAcceuil.setBounds(635, 25, 97, 25);
		
		btn_retourAcceuil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frameAcceuil.setVisible(true);
				thisframe.dispose();			
			}
		});

		contentPane.add(btn_retourAcceuil);
					
	}
}
