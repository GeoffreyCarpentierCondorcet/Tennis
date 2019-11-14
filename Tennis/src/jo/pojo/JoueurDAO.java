package jo.pojo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jo.business.Joueur;

public class JoueurDAO extends DAO<Joueur>{
	public JoueurDAO(Connection connect) {
		super(connect);
		
	}
	public boolean create(Joueur obj) {
		return false;
	}
	public boolean delete(Joueur obj) {
		return false;
	}
	public boolean update(Joueur obj) {
		return false;
	}
	public Joueur find(int id) {
		Joueur Joueur = new Joueur();
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Personne WHERE id = " + id);
					if(result.first())
						Joueur = new Joueur(id, result.getString("nom"),result.getString("sexe"));
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return Joueur;
	}
	public ArrayList<Joueur> getListObjects() {
		ArrayList<Joueur> a = new ArrayList<Joueur>();
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Personne WHERE id IN "
							+ "(SELECT id FROM joueur)");
					while(result.next())
						a.add(new Joueur(result.getInt("id"), result.getString("nom"),result.getString("sexe")));
		}
	
		catch(SQLException e){
			e.printStackTrace();
		}
		return a;
	}
}
