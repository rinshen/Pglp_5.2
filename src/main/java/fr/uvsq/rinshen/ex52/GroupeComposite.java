package fr.uvsq.rinshen.ex52;

import java.util.ArrayList;

/**
 * Classe pouvant contenir des personnels et/ou des sous groupes de personnels.
 */
public class GroupeComposite extends Groupe {
	private static final long serialVersionUID = -5967645000430610085L;
	private ArrayList<Groupe> sousGroupes;
	
	public GroupeComposite() {
		super();
		idType = 1;
		sousGroupes = new ArrayList<Groupe>();
	}
	
	public void ajouteGroupe(Groupe groupe) {
		sousGroupes.add(groupe);
	}
	
	public ArrayList<Groupe> getSousGroupes() {
		return sousGroupes;
	}
	
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
