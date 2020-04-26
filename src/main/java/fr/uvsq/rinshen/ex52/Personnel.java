package fr.uvsq.rinshen.ex52;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;

public final class Personnel implements Serializable {
	private static final long serialVersionUID = -7650298502994864573L;
	private final int id;
	private final String nom;
	private final String prenom;
	private final String fonction;
	private final LocalDate dateNaissance;
	private ArrayList<String> numTelephone;

	public static class Builder {
		private String nom;
		private String prenom;
		private String fonction;
		private LocalDate dateNaissance;
		private ArrayList<String> numTelephone;
		private static int compteur = 0;
		private int id;

		/**
	     * Constructeur du builder de la classe personnel.
	     * @param nom1 -> Nom de la personne
	     * @param nom2 -> Prénom de la personne
	     */
		public Builder(String nom1, String nom2) {
			nom = nom1;
			prenom = nom2;
			id = compteur;
			compteur++;
			fonction = "non spécifiée";
			dateNaissance = LocalDate.of(0, Month.JANUARY, 1);
			numTelephone = new ArrayList<String>();
		}

		public Builder fonction(String role) {
			fonction = role;
			return this;
		}

		public Builder date_naissance(int jour, Month mois, int annee) {
			dateNaissance = LocalDate.of(annee, mois, jour);
			return this;
		}

		public Builder date_naissance(LocalDate date) {
			dateNaissance = date;
			return this;
		}
		 
		public Builder id(int identifiant) {
			id = identifiant;
			return this;
		}

		public Builder num_telephone(String numero) {
			numTelephone.add(numero);
			return this;
		}

		public Personnel build() {
			return new Personnel(this);
		}
	}

	/**
     * Constructeur de la classe Personnel.
     * @param builder -> Builder permettant l'initialisaton de la classe
     */
	public Personnel(Builder builder) {
		id = builder.id;
		nom = builder.nom;
		prenom = builder.prenom;
		fonction = builder.fonction;
		dateNaissance = builder.dateNaissance;
		numTelephone = builder.numTelephone;
	}

	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public String getFonction() {
		return fonction;
	}

	public LocalDate getDateNaissance() {
		return dateNaissance;
	}

	public ArrayList<String> getNumTelephone() {
		return numTelephone;
	}
	
	public void ajouteTelephone(String num) {
		numTelephone.add(num);
	}
	
	public void setTelephone(ArrayList<String> liste) {
		numTelephone = liste;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Personnel)) {
			return false;
		}
		Personnel test = (Personnel)obj;
		if (!this.numTelephone.equals(test.getNumTelephone())) {
			return false;
		}
		if (!this.nom.equals(test.getNom())) {
			return false;
		}
		if (!this.prenom.equals(test.getPrenom())) {
			return false;
		}
		if (!this.fonction.equals(test.getFonction())) {
			return false;
		}
		if (!this.dateNaissance.equals(test.getDateNaissance())) {
			return false;
		}
		if (this.id != test.getId()) {
			return false;
		}
		return true;
	}

	public int getId() {
		return id;
	}
}
