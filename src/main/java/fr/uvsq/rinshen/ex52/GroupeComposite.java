package fr.uvsq.rinshen.ex52;

import java.util.ArrayList;

/**
 * Classe pouvant contenir des personnels et/ou des sous groupes de personnels.
 */
public class GroupeComposite extends Groupe {
	//liste des sous groupes (peut contenir n'importe quelle classe héritant de Groupe
	private ArrayList<Groupe> sousGroupes;
	
	/**
	 * Constructeur du GroupeComposite.
	 */
	public GroupeComposite() {
		super();
		idType = 1;
		sousGroupes = new ArrayList<Groupe>();
	}
	
	/**
	 * Fonction ajoutant un objet héritant de Groupe à la liste des sous groupes.
	 * @param groupe -> Groupe à ajouter
	 */
	public void ajouteGroupe(Groupe groupe) {
		sousGroupes.add(groupe);
	}
	
	public ArrayList<Groupe> getSousGroupes() {
		return sousGroupes;
	}
	
	/**
	 * Fonction d'égalité utilisée pour les tests.
	 * @param obj -> Objet à comparer
	 * @return true si les objets ont le même type et les même attributs
	 */
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof GroupeComposite)) {
			return false;
		}
		GroupeComposite test = (GroupeComposite)obj;
		if (!this.getMembres().equals(test.getMembres())) {
			return false;
		}
		if (!this.sousGroupes.equals(test.sousGroupes)) {
			return false;
		}
		return true;
	}
}
