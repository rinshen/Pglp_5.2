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

public class GroupeCompositeDao implements DataAccessObject<GroupeComposite> {

	public GroupeCompositeDao() {
		;
	}

	/**
     * Fonction permettant l'écriture d'un groupe dans un fichier.
     * @param obj -> Groupe à sérialiser
     * @param fichier -> Nom du Fichier dans lequel l'objet sera enregistré
     */
	public void ecrire(GroupeComposite obj, String fichier) {
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
     * Fonction permettant la lecture d'un fichier contenant un groupe de personnels.
     * @param fichier -> nom du fichier contenant les données
     */
	public GroupeComposite lire(String fichier) {
		GroupeComposite g = null;
		try {
			ObjectInputStream entree;
			entree = new ObjectInputStream(new BufferedInputStream(
					new FileInputStream(new File(fichier))));
			g = (GroupeComposite)entree.readObject();
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
		return g;
	}
}
