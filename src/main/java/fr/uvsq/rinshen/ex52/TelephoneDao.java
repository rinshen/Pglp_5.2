package fr.uvsq.rinshen.ex52;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TelephoneDao {
	private Statement db;
	
	public TelephoneDao(Statement database) {
		db = database;
	}

	/**
     * Fonction permettant l'enregistrement des numéros de téléphone dans la bdd.
     * @param obj -> Propriétaire des numéros de téléphone
     */
	public void ecrire(Personnel obj) {
		try {
			for (int i = 0; i < obj.getNumTelephone().size(); i++) {
				db.executeUpdate("insert into telephone values ('"
						+ obj.getNumTelephone().get(i) + "',"
						+ obj.getId() + ")");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
     * Fonction permettant la lecture de la table telephone.
     * @param id -> Identifiant de la personne propriétaire des numéros
     * @return ArrayList contenant les numéros de téléphone de la personne
     */
	public ArrayList<String> lire(int id) {
		ArrayList<String> res = new ArrayList<String>();
		try {
			ResultSet table = db.executeQuery("select num from telephone "
					+ "where id = "
					+ id);
			while (table.next()) {
				res.add(table.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public void supprimer(int id) {
		try {
			db.executeUpdate("delete from telephone where id = " + id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
