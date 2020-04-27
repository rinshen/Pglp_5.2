package fr.uvsq.rinshen.ex52;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GroupeCompositeDao implements DataAccessObject<GroupeComposite> {
	private Statement db;

	public GroupeCompositeDao(Statement database) {
		db = database;
	}

	public void ecrireMembres(GroupeComposite obj) {
		for (int i = 0; i < obj.getMembres().size(); i++) {
				FabriqueDao.creerPersonnelDao().ecrire(obj.getMembres().get(i));
		}
	}
	
	public void ecrireGroupes(GroupeComposite obj) {
		for (int i = 0; i < obj.getSousGroupes().size(); i++) {
			if(obj.getSousGroupes().get(i).getIdType() == 1) {
				try {
					db.executeUpdate("insert into composite values ("
							+ obj.getSousGroupes().get(i).getId() + ","
							+ obj.getId() + ")");
				} catch (SQLException e) {
					e.printStackTrace();
				}
				FabriqueDao.creerCompositeDao().ecrire((GroupeComposite) obj.getSousGroupes().get(i));
			}
			if(obj.getSousGroupes().get(i).getIdType() == 2) {
				try {
					db.executeUpdate("insert into composite values ("
							+ obj.getSousGroupes().get(i).getId() + ","
							+ obj.getId() + ")");
				} catch (SQLException e) {
					e.printStackTrace();
				}
				FabriqueDao.creerFeuilleDao().ecrire((GroupeFeuille) obj.getSousGroupes().get(i));
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
		/*db.executeUpdate("insert into typeGroupe values ("
				+ obj.getId() + ","
				+ obj.getIdType() + ")");*/
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
					+ id
					);
			//System.out.println(table.next());
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
	
	public void supprimer(int id) {
		try {
			db.executeUpdate("delete from composite where idComposite = " + id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void fermeture(){
		try {
			db.getConnection().close();
			db.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
