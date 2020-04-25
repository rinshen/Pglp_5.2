package fr.uvsq.rinshen.ex52;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class FabriqueDao {
	public static Statement connexion() {
		try {
			String url="jdbc:derby:./database/personnel;create=true";
			Connection c = DriverManager.getConnection(url);
			Statement db = c.createStatement();
			return db;
		} catch (SQLException e) {
			System.out.println("Impossible de se connecter à la base de données");
			e.printStackTrace();
		}
		return null;
	}
	
	public static void initTelephone(Statement db) {
		try {
			db.executeUpdate("create table telephone ("
					+ "num varchar(10) primary key, "
					+ "nom varchar(30), "
					+ "prenom varchar(30))");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
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
	
	public static void initBdd(Statement db) {
		initTelephone(db);
		initPersonnel(db);
	}
	
	public static void effaceTelephone(Statement db) {
		try {
			db.executeUpdate("Drop table telephone");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void effacePersonnel(Statement db) {
		try {
			db.executeUpdate("Drop table Personnel");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void effaceBdd(Statement db) {
		effaceTelephone(db);
		effacePersonnel(db);
	}
	
	public static void resetBdd(Statement db) {
		effaceBdd(db);
		initBdd(db);
		System.out.println("Reset de la base de données");
	}
	
	public static GroupeCompositeDao creerCompositeDao() {
		Statement db = FabriqueDao.connexion();
		return new GroupeCompositeDao(db);
	}
	
	public static GroupeFeuilleDao creerFeuilleDao() {
		Statement db = FabriqueDao.connexion();
		return new GroupeFeuilleDao(db);
	}
	
	public static PersonnelDao creerPersonnelDao() {
		Statement db = FabriqueDao.connexion();
		return new PersonnelDao(db);
	}
	
	public static TelephoneDao creerTelephoneDao() {
		Statement db = FabriqueDao.connexion();
		return new TelephoneDao(db);
	}
}
