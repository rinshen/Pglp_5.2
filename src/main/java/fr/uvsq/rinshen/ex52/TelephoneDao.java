package fr.uvsq.rinshen.ex52;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TelephoneDao{
	private Statement db;
	
	public TelephoneDao(Statement database) {
		db = database;
	}

	public void ecrire(Personnel obj) {
		try {
			for(int i = 0; i < obj.getNumTelephone().size(); i++) {
				db.executeUpdate("insert into telephone values ('"
						+ obj.getNumTelephone().get(i) + "',"
						+ obj.getId() + ")");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<String> lire(int id) {
		ArrayList<String> res = new ArrayList<String>();
		try {
			ResultSet tableau = db.executeQuery("select num from telephone "
					+ "where id = "
					+ id);
			while (tableau.next()) {
				res.add(tableau.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
}
