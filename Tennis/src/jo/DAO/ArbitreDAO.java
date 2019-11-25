package jo.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jo.business.Arbitre;
import jo.business.Personne;


public class ArbitreDAO extends DAO<Arbitre>{
	public ArbitreDAO(Connection connect) {
		super(connect);
		
	}
	public boolean create(Arbitre obj) {
		return false;	
	}
	public boolean delete(Arbitre obj) {
		return false;
	}
	public boolean update(Arbitre obj) {
		return false;
	}
	public Arbitre find(int id) {
		Arbitre arbitre = new Arbitre();
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Arbitre A INNER JOIN Personne B ON A.id=B.id WHERE id =" + id);
					if(result.first())
						arbitre = new Arbitre(id, result.getString("nom"),result.getString("sexe"));
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return arbitre;
	}
	public ArrayList<Arbitre> getListObjects() {
		ArrayList<Arbitre> a = new ArrayList<Arbitre>();
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Arbitre A INNER JOIN Personne B ON A.id=B.id");
					while(result.next())
						a.add(new Arbitre(result.getInt("id"), result.getString("nom"),result.getString("sexe")));
		}	
		catch(SQLException e){
			e.printStackTrace();
		}
		return a;
	}
}
