package jo.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jo.business.Joueur;
import jo.business.Personne;

public class PersonneDAO extends DAO<Personne>{
	public PersonneDAO(Connection connect) {
		super(connect);		
	}
	public boolean create(Personne obj) {
		String query = "INSERT INTO Personne (nom, sexe) VALUES (?,?)";
		try {
			PreparedStatement p = connect.prepareStatement(query);
			p.setString(1,obj.getNom());
			p.setString(2,obj.getSexe());
			p.executeUpdate();
			return true;
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	public boolean delete(Personne obj) {
		return false;
	}
	public boolean update(Personne obj) {
		return false;
	}
	public Personne find(int id) {
		Personne Personne = new Personne();
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Personne WHERE id = " + id);
					if(result.first())
						Personne = new Personne(id, result.getString("nom"),result.getString("sexe"));
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return Personne;
	}
	public ArrayList<Personne> getListObjects() {
		ArrayList<Personne> a = new ArrayList<Personne>();
		return a;
	}
}
