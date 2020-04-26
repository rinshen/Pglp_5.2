package fr.uvsq.rinshen.ex52;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Classe de base pour contenir des listes de personnes.
 */
public abstract class Groupe implements Serializable {
	private static final long serialVersionUID = -1072913158732325451L;
	private static int compteur = 0;
	protected int idType = 0;
	private int id;
	private ArrayList<Personnel> membres;
	
	public Groupe() {
		membres = new ArrayList<Personnel>();
		id = compteur;
		compteur++;
	}
	
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
