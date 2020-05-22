package fr.uvsq.rinshen.ex52;

/**
 * Groupe contenant uniquement des personnes.
 */
public class GroupeFeuille extends Groupe {
	public GroupeFeuille() {
		super();
		idType = 2;
	}
	
	/**
	 * Fonction d'égalité utilisée pour les tests.
	 * @param obj -> Objet à comparer
	 * @return true si les objets ont le même type et les même attributs
	 */
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof GroupeFeuille)) {
			return false;
		}
		GroupeFeuille test = (GroupeFeuille)obj;
		if (!this.getMembres().equals(test.getMembres())) {
			return false;
		}
		return true;
	}
}
