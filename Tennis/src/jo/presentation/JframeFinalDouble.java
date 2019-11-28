package jo.presentation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import jo.business.Equipe;
import jo.business.Match;

public class JframeFinalDouble extends JFrame {
	private ArrayList<JLabel> aJLabel = new ArrayList<JLabel>();

	private JPanel contentPane;
	private int compteur = 0;
	int y = 100;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public JframeFinalDouble(Match m) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setVisible(true);
		
		for(Equipe e : m.getEquipes()) {
			aJLabel.add(new JLabel(e.getA().get(0).getNom() + " / " + e.getA().get(1).getNom()));
			aJLabel.get(compteur).setBounds(100, y, 150, 25);
			aJLabel.get(compteur).setBorder(new LineBorder(new Color(0, 0, 0)));
			contentPane.add(aJLabel.get(compteur));
			compteur++;	
			y+=25;
		}
	}
}
