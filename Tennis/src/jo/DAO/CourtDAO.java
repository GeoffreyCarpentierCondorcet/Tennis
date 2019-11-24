package jo.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jo.business.Court;
import jo.business.Joueur;

public class CourtDAO extends DAO<Court>{
	public CourtDAO(Connection connect) {
		super(connect);
	}
	public boolean create(Court obj) {
		return false;
	}
	public boolean delete(Court obj) {
		return false;
	}
	public boolean update(Court obj) {
		return false;
	}
	public Court find(int id) {
		Court court = new Court();
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Court WHERE id = " + id);
					if(result.first())
						court = new Court(id, result.getString("nomCourt"));
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return court;
	}
	public ArrayList<Court> getListObjects() {
		ArrayList<Court> a = new ArrayList<Court>();
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Court");
					while(result.next())
						a.add(new Court(result.getInt("id"), result.getString("nomCourt")));
		}
	
		catch(SQLException e){
			e.printStackTrace();
		}
		return a;
	}
}
