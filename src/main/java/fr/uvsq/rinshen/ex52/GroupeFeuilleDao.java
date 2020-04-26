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
     * Fonction permettant l'enregistrement d'un groupe dans la base de données.
     * @param obj -> Groupe à enregistrer
     */
	public void ecrire(GroupeFeuille obj) {
		try {
			for (int i = 0; i < obj.getMembres().size(); i++) {
				System.out.println("1 écriture de :" + obj.getMembres().get(i).getId() + "," + obj.getId());
				db.executeUpdate("insert into feuille values ("
						+ obj.getMembres().get(i).getId() + ","
						+ obj.getId() + ")");
				FabriqueDao.creerPersonnelDao().ecrire(obj.getMembres().get(i));
				System.out.println("2 écriture de :" + obj.getMembres().get(i).getId() + "," + obj.getId());
				/*db.executeUpdate("insert into typeGroupe values ("
						+ obj.getId() + ","
						+ obj.getIdType() + ")");*/
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
     * Fonction permettant de récupérer un groupe dans la base de données.
     * @param id -> id du groupe à récupérer
     */
	public GroupeFeuille lire(int id) {
		GroupeFeuille g = new GroupeFeuille();
		Personnel p;
		try {
			ResultSet table = db.executeQuery("select idPersonnel from feuille "
					+ "where idGroupe = "
					+ id);
			//System.out.println("Table du groupe :"+table.next());
			while (table.next()) {
				p = FabriqueDao.creerPersonnelDao().lire(table.getInt(1));
				g.ajouteMembre(p);
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
