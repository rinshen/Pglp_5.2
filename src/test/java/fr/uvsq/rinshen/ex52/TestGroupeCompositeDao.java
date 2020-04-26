package fr.uvsq.rinshen.ex52;

import static org.junit.Assert.*;

import java.time.Month;

import org.junit.Before;
import org.junit.Test;

public class TestGroupeCompositeDao {
	@Before
	public void setUp() {
		FabriqueDao.resetBdd(FabriqueDao.connexion());
	}
	
	@Test
	public void test() {
		Personnel p4 = new Personnel.Builder("Rogg","Waldric")
				.date_naissance(15, Month.JULY, 1996)
				.fonction("Docteur")
				.num_telephone("0658423215")
				.build();
		Personnel p3 = new Personnel.Builder("Drek","Goliath")
				.date_naissance(26, Month.JANUARY, 1997)
				.fonction("Infirmier")
				.num_telephone("0621658719")
				.build();
		Personnel p2 = new Personnel.Builder("Yaheza","Alexandra")
				.date_naissance(15, Month.JULY, 1996)
				.fonction("Indescriptible")
				.num_telephone("0625648257")
				.build();
		Personnel p1 = new Personnel.Builder("Sofia","Beleyan")
				.date_naissance(26, Month.JANUARY, 1997)
				.fonction("Indéfinie")
				.num_telephone("0655892225")
				.build();
		GroupeFeuille g1 = new GroupeFeuille();
		GroupeFeuille g2 = new GroupeFeuille();
		
		GroupeComposite c1 = new GroupeComposite();
		GroupeComposite c2 = null;

		g1.ajouteMembre(p1);
		g1.ajouteMembre(p2);
		g2.ajouteMembre(p3);
		g2.ajouteMembre(p4);
		
		c1.ajouteGroupe(g1);
		c1.ajouteGroupe(g2);
		
		GroupeCompositeDao dao = FabriqueDao.creerCompositeDao();
		dao.ecrire(c1);
		c2 = dao.lire(c1.getId());
		
		assertEquals(c1, c2);
	}
}
