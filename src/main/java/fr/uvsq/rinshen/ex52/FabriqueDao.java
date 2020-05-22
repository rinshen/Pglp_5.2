package fr.uvsq.rinshen.ex52;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Classe utilisée pour instancier les DAO et initialiser la base de données.
 * @author khanj
 *
 */
public class FabriqueDao {
	/**
	 * Charge la base de données.
	 * @return Statement pointant sur la base de données
	 */
	public static Statement connexion() {
		try {
			String url = "jdbc:derby:./database/personnel;create=true";
			Connection c = DriverManager.getConnection(url);
			Statement db = c.createStatement();
			return db;
		} catch (SQLException e) {
			System.out.println("Impossible de se connecter à la base de données");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Déclaration de la table telephone.
	 * @param db -> Statement pointant sur la base de données à modifier
	 */
	public static void initTelephone(Statement db) {
		try {
			db.executeUpdate("create table telephone ("
					+ "num varchar(10) primary key, "
					+ "id int)");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Déclaration de la table personnel.
	 * @param db -> Statement pointant sur la base de données à modifier
	 */
	public static void initPersonnel(Statement db) {
		try {
			db.executeUpdate("create table personnel ("
					+ "id int primary key, "
					+ "nom varchar(30), "
					+ "prenom varchar(30), "
					+ "fonction varchar(30), "
					+ "dateNaissance date)");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Déclaration de la table typeGroupe.
	 * @param db -> Statement pointant sur la base de données à modifier
	 */
	public static void initType(Statement db) {
		try {
			db.executeUpdate("create table typeGroupe ("
					+ "id int primary key, "
					+ "type int)");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Déclaration de la table feuille.
	 * @param db -> Statement pointant sur la base de données à modifier
	 */
	public static void initFeuille(Statement db) {
		try {
			db.executeUpdate("create table feuille ("
					+ "idPersonnel int primary key, "
					+ "idGroupe int)");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Déclaration de la table composite.
	 * @param db -> Statement pointant sur la base de données à modifier
	 */
	public static void initComposite(Statement db) {
		try {
			db.executeUpdate("create table composite ("
					+ "idFeuille int primary key, "
					+ "idComposite int)");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Initialise la base de données.
	 * @param db -> Statement pointant sur la base de données à modifier
	 */
	public static void initBdd(Statement db) {
		initTelephone(db);
		initPersonnel(db);
		initFeuille(db);
		initComposite(db);
		initType(db);
	}
	

	/**
	 * Efface la table telephone.
	 * @param db -> Statement pointant sur la base de données à modifier
	 */
	public static void effaceTelephone(Statement db) {
		try {
			db.executeUpdate("Drop table telephone");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Efface la table personnel.
	 * @param db -> Statement pointant sur la base de données à modifier
	 */
	public static void effacePersonnel(Statement db) {
		try {
			db.executeUpdate("Drop table personnel");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Efface la table typeGroupe.
	 * @param db -> Statement pointant sur la base de données à modifier
	 */
	public static void effaceType(Statement db) {
		try {
			db.executeUpdate("Drop table typeGroupe");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Efface la table feuille.
	 * @param db -> Statement pointant sur la base de données à modifier
	 */
	public static void effaceFeuille(Statement db) {
		try {
			db.executeUpdate("Drop table feuille");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Efface la table composite.
	 * @param db -> Statement pointant sur la base de données à modifier
	 */
	public static void effaceComposite(Statement db) {
		try {
			db.executeUpdate("Drop table composite");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Efface le contenu de la base de données.
	 * @param db -> Statement pointant sur la base de données à modifier
	 */
	public static void effaceBdd(Statement db) {
		effaceTelephone(db);
		effacePersonnel(db);
		effaceFeuille(db);
		effaceComposite(db);
		effaceType(db);
	}
	
	/**
	 * Efface la base de données et en recrée une complètment vide.
	 * @param db -> Statement pointant sur la base de données à réinitialiser
	 */
	public static void resetBdd(Statement db) {
		effaceBdd(db);
		initBdd(db);
	}
	
	/**
	 * Instanciation d'un GroupeCompositeDao.
	 * @return GroupeCompositeDao initialisé
	 */
	public static GroupeCompositeDao creerCompositeDao() {
		Statement db = FabriqueDao.connexion();
		return new GroupeCompositeDao(db);
	}

	/**
	 * Instanciation d'un GroupeFeuilleDao.
	 * @return GroupeFeuilleDao initialisé
	 */
	public static GroupeFeuilleDao creerFeuilleDao() {
		Statement db = FabriqueDao.connexion();
		return new GroupeFeuilleDao(db);
	}

	/**
	 * Instanciation d'un PersonnelDao.
	 * @return PersonnelDao initialisé
	 */
	public static PersonnelDao creerPersonnelDao() {
		Statement db = FabriqueDao.connexion();
		return new PersonnelDao(db);
	}

	/**
	 * Instanciation d'un TelephoneDao.
	 * @return TelephoneDao initialisé
	 */
	public static TelephoneDao creerTelephoneDao() {
		Statement db = FabriqueDao.connexion();
		return new TelephoneDao(db);
	}
}
