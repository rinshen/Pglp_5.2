package fr.uvsq.rinshen.ex52;

import java.util.ArrayList;

/**
 * Classe de base pour contenir des listes de personnes.
 */
public abstract class Groupe {
	private static int compteur = 0;//Utilisé pour l'initialisation de id
	protected int idType = 0;//Type du groupe (Feuille ou Composite)
	private int id;//Identifiant unique servant de clé primaire dans la base de données
	private ArrayList<Personnel> membres;
	
	/**
	 * Constructeur du Groupe.
	 */
	public Groupe() {
		membres = new ArrayList<Personnel>();
		id = compteur;
		compteur++;
	}
	
	/**
	 * Ajoute un Personnel à la liste des personnes.
	 * @param nouveau -> Personnel à ajouter
	 */
	public void ajouteMembre(Personnel nouveau) {
		membres.add(nouveau);
	}
	
	public ArrayList<Personnel> getMembres() {
		return membres;
	}

	public int getId() {
		return id;
	}

	public int getIdType() {
		return idType;
	}
}
