package se.centernode.javaapitest.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import se.centernode.javaapitest.entity.Statement;
import se.centernode.javaapitest.entity.StatementLink;
import se.centernode.javaapitest.enums.State;
import se.centernode.javaapitest.enums.StatementType;

@DataJpaTest
class StatementServiceTest {
	
	@Autowired
	StatementService service;
	
	@Autowired
	StatementLinkService linkService;
	
	Statement statement = new Statement(
			StatementType.STATEMENT,
			"Anders_Orback",
			State.UNRESOLVED,
			"All grass is green.");
	
	@Test
	void test() {
		// Assert link repository is empty
		assertEquals(0, service.count());
		
		// Assert statement repository is empty
		assertEquals(0,service.count());
		
		Statement savedStatement = service.save(statement);
		// Assert one statement has been saved
		assertEquals(1,service.count());
		
		Statement updateStatement= service.updateAndSave(savedStatement, "Most grass is green");
		assertEquals(2, updateStatement.getId());
		assertEquals(2, updateStatement.getVersion());
		assertEquals("Most grass is green", updateStatement.getText());
		
		// Assert link is added for original statement
		List<StatementLink> sourceLinks = linkService.findAllBySource(statement.getId());
		assertEquals(1, sourceLinks.size());
	}

}
