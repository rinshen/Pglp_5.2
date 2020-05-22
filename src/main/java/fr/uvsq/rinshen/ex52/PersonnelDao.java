package fr.uvsq.rinshen.ex52;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PersonnelDao implements DataAccessObject<Personnel> {
	private Statement db;
	
	public PersonnelDao(Statement database) {
		db = database;
	}

	/**
     * Fonction permettant l'enregistrement d'une personne dans la base de données.
     * @param obj -> Personne à enregistrer
     */
	public void ecrire(Personnel obj) {
		try {
			db.executeUpdate("insert into personnel values ("
					+ obj.getId() + ",'"
					+ obj.getNom() + "','"
					+ obj.getPrenom() + "','"
					+ obj.getFonction() + "','"
					+ obj.getDateNaissance() + "')");
			FabriqueDao.creerTelephoneDao().ecrire(obj);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
     * Fonction permettant la lecture de la table personnel.
     * @param id -> Identifiant de la personne à chercher dans la bdd
     * @return Objet personnel initialisé avec les valeurs trouvées dans la bdd
     */
	public Personnel lire(int id) {
		Personnel p = null;
		ResultSet table;
		try {
			
			table = db.executeQuery("select * from personnel "
					+ "where id = "
					+ id);
			table.next();
			p = new Personnel.Builder(table.getString(2), table.getString(3))
					.date_naissance(table.getDate(5).toLocalDate())
					.fonction(table.getString(4))
					.id(id)
					.build();
			p.setTelephone(FabriqueDao.creerTelephoneDao().lire(id));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return p;
	}

	/**
	 * Fonction permettant la modification d'un Personnel déja présent dans la base de données.
	 * @param obj -> Personnel à modifier
	 */
	public void modifier(Personnel obj) {
		supprimer(obj.getId());
		ecrire(obj);
	}
	
	/**
	 * Fonction permettant la suppression d'un Personnel de la base de données.
	 * @param id -> Identifiant du Personnel à supprimer
	 */
	public void supprimer(int id) {
		try {
			db.executeUpdate("delete from personnel where id = " + id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		FabriqueDao.creerTelephoneDao().supprimer(id);
	}
	
	/**
	 * Fonction permettant la fermeture de la connection à la base de données 
	 * et la libération des ressources.
	 */
	public void fermeture() {
		try {
			db.getConnection().close();
			db.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
