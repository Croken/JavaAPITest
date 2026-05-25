package se.centernode.javaapitest.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import se.centernode.javaapitest.entity.Statement;
import se.centernode.javaapitest.enums.State;
import se.centernode.javaapitest.enums.StatementType;

@DataJpaTest
class StatementTest {
	@Autowired
	StatementService service;
	
	Statement statement = new Statement(
			StatementType.STATEMENT,
			"Anders_Orback",
			State.UNRESOLVED,
			"All grass is green.");
	
	@Test
	void saveAndRetriveTest() {
		service.save(statement);
		
		Statement statement2 = service.findById(statement.getId()).get();
		
		// Statement saved is same as statement .
		assertEquals(statement, statement2);
	}
	
	@Test
	void createUpdateTest() {
		Statement savedStatment = service.save(statement);
		Statement statmentUpdated = new Statement(savedStatment,"Most grass is green.");
		service.save(statmentUpdated);
		
		// update does not overwrite parent.
		assertEquals(2,service.count());
		// version is incremented
		assertEquals(2, statmentUpdated.getVersion());
		
	  assertNotEquals(savedStatment.getId(), statmentUpdated.getId());
		
		// created_time is updated
		assertTrue((statmentUpdated.getCreatedTime() - statement.getCreatedTime() > 0), "Second version shoud allways be created after parent.");
	}
	
	@Test
	void testFindBySid() {
		service.save(statement);
		Statement statmentUpdated = new Statement(statement,"Most grass is green.");
		service.save(statmentUpdated);
		
		service.save(new Statement(
			StatementType.STATEMENT,
			"Anders_Orback",
			State.UNRESOLVED,
			"Something else"));
		
		// all statements added
		assertEquals(3,service.count());
		
		List<Statement> allBySid = service.findAllBySid(statmentUpdated.getSid());
		allBySid.forEach(System.out::println);
		// found both from same sid.
		assertEquals(2, allBySid.size(), "Number of Sid entrys found: " + allBySid.size());
		// latest version first.
		assertEquals(2, allBySid.get(0).getVersion(), "First version number found in list: " + allBySid.get(0).getVersion());
	}
	
	@Test
	void testFindByAuthor() {
		service.save(statement);
		Statement statmentUpdated = new Statement(statement,"Most grass is green.");
		service.save(statmentUpdated);
		
		service.save(new Statement(
			StatementType.STATEMENT,
			"Other_Orback",
			State.UNRESOLVED,
			"Something else"));
		
		// all statements added
		assertEquals(3,service.count());
		
		List<Statement> allByAuthor = service.findAllByAuthor(statmentUpdated.getAuthor());
		allByAuthor.forEach(System.out::println);
		// found both from same sid.
		assertEquals(2, allByAuthor.size(), "Number of author entrys found: " + allByAuthor.size());
		// latest version first.
		assertEquals(2, allByAuthor.get(0).getVersion(), "First version number found in list: " + allByAuthor.get(0).getVersion());
	}

}
