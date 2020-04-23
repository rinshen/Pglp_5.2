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

public class PersonnelDao implements DataAccessObject<Personnel> {
	public PersonnelDao() {
		;
	}

	/**
     * Fonction permettant l'écriture d'une personne dans un fichier.
     * @param obj -> Personne à sérialiser
     * @param fichier -> Nom du Fichier dans lequel l'objet sera enregistré
     */
	public void ecrire(Personnel obj, String fichier) {
		try {
			ObjectOutputStream sortie;
			sortie = new ObjectOutputStream(new BufferedOutputStream(
					new FileOutputStream(new File(fichier))));
			sortie.writeObject(obj);
			sortie.close();
		} catch (FileNotFoundException e) {
			System.out.println("Fichier introuvable");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Erreur d'écriture");
			e.printStackTrace();
		}
	}

	/**
     * Fonction permettant la lecture d'un fichier contenant une personne.
     * @param fichier -> nom du fichier contenant les données
     */
	public Personnel lire(String fichier) {
		Personnel p = null;
		try {
			ObjectInputStream entree;
			entree = new ObjectInputStream(new BufferedInputStream(
					new FileInputStream(new File(fichier))));
			p = (Personnel)entree.readObject();
			entree.close();
		} catch (FileNotFoundException e) {
			System.out.println("Fichier introuvable");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Erreur de lecture");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("Fichier incorrect");
			e.printStackTrace();
		}
		return p;
	}
}
