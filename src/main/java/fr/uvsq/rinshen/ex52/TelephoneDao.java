package fr.uvsq.rinshen.ex52;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Classe Permettant l'enregistrement des numéros de téléphone d'un Personnel.
 * Elle n'implémente pas l'interface DataAccessObjet car elle manipule à la
 * fois des ArrayList et des personnels
 */
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

	/**
     * Fonction permettant la modification de la liste des numéros de téléphone d'un personnel.
     * @param obj -> Propriétaire des numéros
     */
	public void modifier(Personnel obj) {
		supprimer(obj.getId());
		ecrire(obj);
	}
	
	/**
     * Fonction permettant suppression des numéros de téléphone d'une personne..
     * @param id -> Identifiant de la personne propriétaire des numéros
     */
	public void supprimer(int id) {
		try {
			db.executeUpdate("delete from telephone where id = " + id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
