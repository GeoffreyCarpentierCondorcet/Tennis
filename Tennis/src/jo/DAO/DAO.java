package jo.DAO;

import java.sql.Connection;
import java.util.ArrayList;

public abstract class DAO<T> {
	protected Connection connect = null;
	public DAO(Connection connect ) {
		this.connect = connect;
	}
	public abstract boolean create(T obj);
	public abstract boolean delete(T obj);
	public abstract T find (int id);
	public abstract ArrayList<T> getListObjects();
}
