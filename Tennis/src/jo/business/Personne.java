package jo.business;
import java.io.Serializable;

public class Personne implements Serializable{
	private static final long serialVersionUID=2;
	private int id;
	private String nom;
	private String sexe;
	
	public Personne() {}
	public Personne(int id, String nom, String sexe) {
		this.id = id;
		this.nom = nom;
		this.sexe = sexe;
	}
	public int getId() {
		return id;
	}
	public String getNom() {
		return nom;
	}
	public String getSexe() {
		return sexe;
	}
}
