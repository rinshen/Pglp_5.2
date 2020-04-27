package fr.uvsq.rinshen.ex52;

import static org.junit.Assert.*;

import java.time.Month;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import fr.uvsq.rinshen.ex52.Personnel;

public class TestPersonnelDao {
	@Before
	public void setUp() {
		FabriqueDao.resetBdd(FabriqueDao.connexion());
	}
	
	//@Test
	public void testEcritureTelephone() {
		ArrayList<String> test;
		TelephoneDao dao = FabriqueDao.creerTelephoneDao();
		Personnel p1 = new Personnel.Builder("Beleyan", "Sofia")
				.num_telephone("0655892225")
				.build();
		p1.ajouteTelephone("0563251478");

		dao.ecrire(p1);
		test = dao.lire(p1.getId());
		assertEquals(p1.getNumTelephone(), test);
	}

	//@Test
	public void testEcriturePersonnel() {
		Personnel p1 = new Personnel.Builder("Beleyan", "Sofia")
				.date_naissance(26, Month.JANUARY, 1997)
				.fonction("Indéfinie")
				.num_telephone("0655892225")	
				.build();
		Personnel p2 = null;
		PersonnelDao dao = FabriqueDao.creerPersonnelDao();
		
		dao.ecrire(p1);
		p2 = dao.lire(p1.getId());
		dao.fermeture();
		assertEquals(p1, p2);
	}
}
