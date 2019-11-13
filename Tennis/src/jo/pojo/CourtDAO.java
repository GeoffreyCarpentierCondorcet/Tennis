package jo.pojo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import jo.business.Court;

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
}
