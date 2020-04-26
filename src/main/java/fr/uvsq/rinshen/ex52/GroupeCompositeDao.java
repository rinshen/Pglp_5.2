package fr.uvsq.rinshen.ex52;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GroupeCompositeDao implements DataAccessObject<GroupeComposite> {
	private Statement db;

	public GroupeCompositeDao(Statement database) {
		db = database;
	}

	/**
     * Fonction permettant l'enregistrement d'un groupe composite dans la base de données.
     * @param obj -> Groupe à enregistrer
     */
	public void ecrire(GroupeComposite obj) {
		try {
			for (int i = 0; i < obj.getMembres().size(); i++) {
				db.executeUpdate("insert into feuille values ("
						+ obj.getMembres().get(i).getId() + ","
						+ obj.getId() + ")");
				db.executeUpdate("insert into composite values ("
						+ obj.getSousGroupes().get(i).getId() + ","
						+ obj.getId() + ")");
				/*db.executeUpdate("insert into typeGroupe values ("
						+ obj.getId() + ","
						+ obj.getIdType() + ")");*/
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
     * Fonction permettant la recherche d'un groupe composite dans la base de données.
     * @param id -> id du groupe à récupérer
     */
	public GroupeComposite lire(int id) {
		GroupeComposite g = new GroupeComposite();
		GroupeFeuille gtmp;
		Personnel ptmp;
		try {
			ResultSet table;
			table = db.executeQuery("select idFeuille from composite "
					+ "where idComposite = "
					+ id);
			while (table.next()) {
				gtmp = FabriqueDao.creerFeuilleDao().lire(table.getInt(1));
				g.ajouteGroupe(gtmp);
			}
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
	
	public void fermeture(){
		try {
			db.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
