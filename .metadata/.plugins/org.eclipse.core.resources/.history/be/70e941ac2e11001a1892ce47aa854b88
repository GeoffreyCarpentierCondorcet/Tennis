package jo.presentation;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.time.LocalDateTime;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import jo.business.Tournoi;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JframeAcceuil extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JframeAcceuil frame = new JframeAcceuil();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JframeAcceuil() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 808, 628);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JframeAcceuil thisframe = this; // permet d'envoyer l'objet dans un autre objet afin de le recup par la suite
		
		Tournoi t = new Tournoi(LocalDateTime.of(2020, 5, 18, 8, 0));
		
		JButton btn_commencerTournoi = new JButton("Commencer tournoi");
		JButton btn_resultatMessieurs = new JButton("resultats messieurs");
		JButton btn_resultatDames = new JButton("resultats dames");
		JButton btn_resultatDoubleMessieurs = new JButton("resultats double messieurs");
		btn_commencerTournoi.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent arg0) {
					t.startAllBrackets();
					
					btn_resultatMessieurs.setVisible(true);
					btn_resultatDames.setVisible(true);
					btn_resultatDoubleMessieurs.setVisible(true);
					JframeMenu frameM = new JframeMenu(thisframe,t);
					thisframe.setVisible(false);
				}
			});
	
		btn_resultatMessieurs.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent arg0) {
				JframeBracket frame = new JframeBracket(thisframe, t.getAo().get(0)); // envoi de l'objet acceuil et de l'ordennencement des messieurs
				thisframe.setVisible(false);
				}
			});
		
		btn_resultatDames.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent arg0) {
				}
			});
		
		btn_resultatDoubleMessieurs.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent arg0) {
				}
			});
			
		btn_commencerTournoi.setBounds(302, 100, 160, 25);
		btn_resultatMessieurs.setBounds(302, 326, 160, 25);
		btn_resultatDames.setBounds(302, 364, 160, 25);
		btn_resultatDoubleMessieurs.setBounds(286, 402, 194, 25);
		
		btn_resultatMessieurs.setVisible(false);
		btn_resultatDames.setVisible(false);
		btn_resultatDoubleMessieurs.setVisible(false);
		
		
		contentPane.add(btn_commencerTournoi);
		contentPane.add(btn_resultatMessieurs);
		contentPane.add(btn_resultatDames);
		contentPane.add(btn_resultatDoubleMessieurs);
			
		
	}
}
