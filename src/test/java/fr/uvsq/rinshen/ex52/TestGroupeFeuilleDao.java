package fr.uvsq.rinshen.ex52;

import static org.junit.Assert.*;

import java.time.Month;

import org.junit.Before;
import org.junit.Test;

public class TestGroupeFeuilleDao {
	@Before
	public void setUp() {
		FabriqueDao.resetBdd(FabriqueDao.connexion());
	}
	
	@Test
	public void testEcritureFeuille() {
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
		GroupeFeuille g2 = null;
		g1.ajouteMembre(p1);
		g1.ajouteMembre(p2);
		
		GroupeFeuilleDao dao = FabriqueDao.creerFeuilleDao();
		dao.ecrire(g1);
		g2 = dao.lire(g1.getId());

		assertEquals(g1, g2);
	}

}
