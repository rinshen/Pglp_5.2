package fr.uvsq.rinshen.ex52;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Classe permettant de lire et d'écrire des GroupeComposite dans la base de données.
 */
public class GroupeCompositeDao implements DataAccessObject<GroupeComposite> {
	private Statement db;

	public GroupeCompositeDao(Statement database) {
		db = database;
	}

	/**
	 * Fonction écrivant les membres du groupe dans la base de données.
	 * @param obj -> objet à écrire
	 */
	public void ecrireMembres(GroupeComposite obj) {
		for (int i = 0; i < obj.getMembres().size(); i++) {
			FabriqueDao.creerPersonnelDao().ecrire(obj.getMembres().get(i));
		}
	}
	
	/**
	 * Fonction écrivant les sous groupes du groupe dans la base de données.
	 * @param obj -> objet à écrire
	 */
	public void ecrireGroupes(GroupeComposite obj) {
		for (int i = 0; i < obj.getSousGroupes().size(); i++) {
			if (obj.getSousGroupes().get(i).getIdType() == 1) {
				try {
					db.executeUpdate("insert into composite values ("
							+ obj.getSousGroupes().get(i).getId() + ","
							+ obj.getId() + ")");
				} catch (SQLException e) {
					e.printStackTrace();
				}
				FabriqueDao.creerCompositeDao().ecrire(
						(GroupeComposite) obj.getSousGroupes().get(i));
			}
			if (obj.getSousGroupes().get(i).getIdType() == 2) {
				try {
					db.executeUpdate("insert into composite values ("
							+ obj.getSousGroupes().get(i).getId() + ","
							+ obj.getId() + ")");
				} catch (SQLException e) {
					e.printStackTrace();
				}
				FabriqueDao.creerFeuilleDao().ecrire(
						(GroupeFeuille) obj.getSousGroupes().get(i));
			}
		}
	}
	
	/**
     * Fonction permettant l'enregistrement d'un groupe composite dans la base de données.
     * @param obj -> Groupe à enregistrer
     */
	public void ecrire(GroupeComposite obj) {
		ecrireMembres(obj);
		ecrireGroupes(obj);
	}

	/**
     * Fonction permettant la recherche d'un groupe composite dans la base de données.
     * @param id -> id du groupe à récupérer
     * @return GroupeComposite initialisé
     */
	public GroupeComposite lire(int id) {
		GroupeComposite g = new GroupeComposite();
		GroupeFeuille gtmp;
		Personnel ptmp;
		try {
			//Lecture des sous groupes
			ResultSet table;
			table = db.executeQuery("select idFeuille from composite "
					+ "where idComposite = "
					+ id
					);
			while (table.next()) {
				gtmp = FabriqueDao.creerFeuilleDao().lire(table.getInt(1));
				g.ajouteGroupe(gtmp);
			}
			//lecture des Personnels
			table = db.executeQuery("select idPersonnel from feuille "
					+ "where idGroupe = "
					+ id);
			while (table.next()) {
				ptmp = FabriqueDao.creerPersonnelDao().lire(table.getInt(1));
				g.ajouteMembre(ptmp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return g;
	}
	
	/**Fonction permettant la modification d'un GroupeComposite déja enregistré
	 * dans la base de données.
	 * @param obj ->Groupe à modifier
	 */
	public void modifier(GroupeComposite obj) {
		supprimer(obj.getId());
		ecrire(obj);
	}
	
	/**
	 * Fonction permettant la suppression d'un GroupeComposite de la base de données.
	 * @param id -> identifiant du groupe à supprimer
	 */
	public void supprimer(int id) {
		try {
			db.executeUpdate("delete from composite where idComposite = " + id);
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
