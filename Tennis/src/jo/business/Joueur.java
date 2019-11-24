package jo.business;
import java.io.Serializable;

public class Joueur extends Personne implements Serializable{
	private static final long serialVersionUID=3;
	
	private int classement;
	
	public Joueur() {}
	public Joueur(int id, String nom, String sexe, int classement) {
		super(id, nom, sexe);
		this.classement = classement;
	}
	public Joueur(String nom, String sexe, int classement) {
		super(nom, sexe);
		this.classement = classement;
	}
	
	
	public int getClassement() {
		return classement;
	}
}
