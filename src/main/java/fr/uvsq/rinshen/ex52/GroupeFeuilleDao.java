package fr.uvsq.rinshen.ex52;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GroupeFeuilleDao implements DataAccessObject<GroupeFeuille> {
	private Statement db;
	
	public GroupeFeuilleDao(Statement database) {
		db = database;
	}

	/**
	 * Fonction d'écriture dans la base de données.
	 * @param obj -> Groupe à enregistrer
	 */
	public void ecrire(GroupeFeuille obj) {
		try {
			for (int i = 0; i < obj.getMembres().size(); i++) {
				db.executeUpdate("insert into feuille values ("
						+ obj.getMembres().get(i).getId() + ","
						+ obj.getId() + ")");
				FabriqueDao.creerPersonnelDao().ecrire(obj.getMembres().get(i));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
     * Fonction permettant de récupérer un groupe dans la base de données.
     * @param id -> id du groupe à récupérer
     * @return GroupeFeuille initialisé
     */
	public GroupeFeuille lire(int id) {
		GroupeFeuille g = new GroupeFeuille();
		Personnel p;
		try {
			ResultSet table = db.executeQuery("select idPersonnel from feuille "
					+ "where idGroupe = "
					+ id);
			while (table.next()) {
				p = FabriqueDao.creerPersonnelDao().lire(table.getInt(1));
				g.ajouteMembre(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return g;
	}
	
	/**
	 * Fonction permettant la modification d'un GroupeFeuille déja enregistré
	 * dans la base de données.
	 * @param obj -> Groupe à modifier
	 */
	public void modifier(GroupeFeuille obj) {
		supprimer(obj.getId());
		ecrire(obj);
	}

	/**
	 * Fonction qui supprime un GroupeFeuille de la base de données.
	 * @param id -> Identifiant du groupe à supprimer
	 */
	public void supprimer(int id) {
		try {
			db.executeUpdate("delete from feuille where idGroupe = " + id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Fonction qui supprime un GroupeFeuille et tout les personnels qu'il
	 * contient de la base de données.
	 * @param id -> Identifiant du groupe à supprimer
	 */
	public void supprimerRecursif(int id) {
		try {
			ResultSet liste = db.executeQuery("select idPersonnel from feuille "
					+ "where idGroupe = "
					+ id);
			while (liste.next()) {
				FabriqueDao.creerPersonnelDao().supprimer(liste.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			db.executeUpdate("delete from feuille where idGroupe = " + id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
