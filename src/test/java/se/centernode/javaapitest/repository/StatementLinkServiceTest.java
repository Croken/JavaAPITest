package se.centernode.javaapitest.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import se.centernode.javaapitest.entity.StatementLink;
import se.centernode.javaapitest.enums.LinkType;

@DataJpaTest
class StatementLinkServiceTest {

	@Autowired
	StatementLinkService service;
	
	private static StatementLink SOURCE = new StatementLink(1l, LinkType.UPDATE, 2l);

	@Test
	void saveTest() {
		assertNull(SOURCE.getId());
		StatementLink save = service.save(SOURCE);

		assertEquals(1l, save.getId());
		assertEquals(1l, save.getStatementIdSource());
		assertEquals(2l, save.getStatementIdPointer());
		assertEquals(LinkType.UPDATE, save.getLinkType());
		
		List<StatementLink> all = service.findAll();
		assertEquals(1, all.size());
	}
	
	@Test
	void deleteTest() {
		StatementLink save = service.save(SOURCE);
		List<StatementLink> all = service.findAll();
		assertEquals(1, all.size());
		
		service.delete(save);
		all = service.findAll();
		assertEquals(0, all.size());
	}
	
	@Test
	void findByIdTest() {
		StatementLink save = service.save(SOURCE);
		StatementLink reource = service.findById(save.getId()).get();
		
		assertEquals(save.getId(), reource.getId());
		assertEquals(SOURCE.getStatementIdSource(), reource.getStatementIdSource());
		assertEquals(SOURCE.getStatementIdPointer(), reource.getStatementIdPointer());
		assertEquals(SOURCE.getLinkType(), reource.getLinkType());
	}
	
	@Test
	void findAllBySourceTest() {
		StatementLink save = service.save(SOURCE);
		
		List<StatementLink> all = service.findAll();
		assertEquals(1, all.size());
		
		// Find all by SourceId
		List<StatementLink> allBySource = service.findAllBySource(1l);
		assertEquals(1, allBySource.size());
		assertEquals(1l, allBySource.get(0).getStatementIdSource());
	}
	
	@Test
	void findAllByPointerTest() {
		StatementLink save = service.save(SOURCE);
		
		List<StatementLink> all = service.findAll();
		assertEquals(1, all.size());
		
		// Find all by PointerId
		List<StatementLink> allBySource = service.findAllByPointer(2l);
		assertEquals(1, allBySource.size());
		assertEquals(2l, allBySource.get(0).getStatementIdPointer());
	}

}
