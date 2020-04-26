package fr.uvsq.rinshen.ex52;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Month;
import java.util.Calendar;

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
     * @param id -> identifiant de la personne à chercher dans la base
     */
	public Personnel lire(int id) {
		Personnel p = null;
		ResultSet table;
		try {
			
			table = db.executeQuery("select * from personnel "
					+ "where id = "
					+ id);
			System.out.println(table.next());
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
}
