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
	
	public static void initBdd(Statement db) {
		try {
			db.executeUpdate("create table telephone ("
					+ "num varchar(10) primary key, "
					+ "nom varchar(30), "
					+ "prenom varchar(30))");
			db.executeUpdate("create table personnel ("
					+ "nom varchar(30) primary key, "
					+ "prenom varchar(30), primary key"
					+ "fonction varchar(30), "
					+ "dateNaissance date)");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void effaceBdd(Statement db) {
		try {
			db.executeUpdate("Drop table telephone");
			db.executeUpdate("Drop table personnel");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void resetBdd(Statement db) {
		initBdd(db);
		effaceBdd(db);
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
