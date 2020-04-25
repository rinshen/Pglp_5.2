package fr.uvsq.rinshen.ex52;

interface DataAccessObject<T> {
	public abstract void ecrire(T obj);

	public abstract T lire(int id);
}
