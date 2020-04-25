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
import java.sql.Statement;

public class GroupeCompositeDao implements DataAccessObject<GroupeComposite> {
	private Statement db;

	public GroupeCompositeDao(Statement database) {
		db = database;
	}

	/**
     * Fonction permettant l'écriture d'un groupe dans un fichier.
     * @param obj -> Groupe à sérialiser
     * @param fichier -> Nom du Fichier dans lequel l'objet sera enregistré
     */
	public void ecrire(GroupeComposite obj) {
		
	}

	/**
     * Fonction permettant la lecture d'un fichier contenant un groupe de personnels.
     * @param fichier -> nom du fichier contenant les données
     */
	public GroupeComposite lire(int id) {
		GroupeComposite g = null;
		return g;
	}
}
