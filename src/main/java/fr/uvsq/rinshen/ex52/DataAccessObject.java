package fr.uvsq.rinshen.ex52;

interface DataAccessObject<T> {
	public abstract void ecrire(T obj, String fichier);

	public abstract T lire(String fichier);
}
