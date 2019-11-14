package jo.business;
import java.io.Serializable;

public class Arbitre extends Personne implements Serializable{
	private static final long serialVersionUID=4;
	
	public Arbitre() {}
	public Arbitre(int id, String nom, String sexe) {
		super(id, nom, sexe);
	}
}
