package com.kratonsolution.belian.partymanagement.impl.repository;

import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.kratonsolution.belian.partymanagement.api.model.PartyType;
import com.kratonsolution.belian.partymanagement.impl.model.Party;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class PartyRepositoryTest
{	
	@Autowired
	private TestEntityManager manager;
	
	@Autowired
	private PartyRepository repository;
	
	@Before
	public void setup() {
		
		manager.persist(new Party("PTY 001", "James Dolan", PartyType.PEOPLE, LocalDate.now(), "NPWP 1010"));
		manager.persist(new Party("PTY 002", "PT ABC Corp", PartyType.ORGANIZATION, LocalDate.now(), "NPWP 2112"));
		manager.flush();
	}
	
	@Test
	public void testCreate() {
		
		Party ppl = new Party("PTY 01", "James Dolan", PartyType.PEOPLE, LocalDate.now(), "NPWP 1010");
		Party org = new Party("PTY 02", "PT ABC Corp", PartyType.ORGANIZATION, LocalDate.now(), "NPWP 2112");

		repository.save(ppl);
		repository.save(org);
		
		Optional<Party> pplOpt = repository.findOneByCode("PTY 01");
		assertTrue(pplOpt.isPresent());
		
		Optional<Party> orgOpt = repository.findOneByCode("PTY 02");
		assertTrue(orgOpt.isPresent());
	}
	
	@Test
	public void testUpdate() {
		
		Optional<Party> pplOpt = repository.findOneByCode("PTY 001");

		assertTrue(pplOpt.isPresent());
		assertTrue(pplOpt.get().getName().equalsIgnoreCase("James Dolan"));
		
		pplOpt.get().update("James Dolan II", null, null, null);
		
		repository.save(pplOpt.get());
		
		pplOpt = repository.findOneByCode("PTY 001");
		
		assertTrue(pplOpt.isPresent());
		assertTrue(pplOpt.get().getName().equalsIgnoreCase("James Dolan II"));
	}
}
