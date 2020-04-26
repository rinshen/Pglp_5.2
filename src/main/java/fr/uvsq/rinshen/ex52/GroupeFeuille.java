package fr.uvsq.rinshen.ex52;

public class GroupeFeuille extends Groupe {
	private static final long serialVersionUID = 7442726568681896649L;

	public GroupeFeuille() {
		super();
		idType = 2;
	}
	
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
