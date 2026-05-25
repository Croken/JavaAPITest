package se.centernode.javaapitest.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import se.centernode.javaapitest.entity.Statement;
import se.centernode.javaapitest.entity.StatementLink;
import se.centernode.javaapitest.enums.LinkType;
import se.centernode.javaapitest.enums.State;
import se.centernode.javaapitest.enums.StatementType;

@Service
@AllArgsConstructor
@Slf4j
public class StatementService {
	
	StatementRepository repo;

	private StatementRepository statementRepository;
	private StatementLinkRepository statementLinkRepository;

	public Statement updateAndSave(Statement statement, String newText) {
		Statement updatedStatement = new Statement(statement, newText);
		updatedStatement = statementRepository.save(updatedStatement);
		
		StatementLink statementLink = new StatementLink(statement.getId(), LinkType.UPDATE, updatedStatement.getId());
		System.out.println("StatmentLink before save: " + statementLink);
		statementLink = statementLinkRepository.save(statementLink);
		System.out.println("StatmentLink after save: " + statementLink);
		
		return updatedStatement;
	}
	
	public Statement addQuestion(long statementId, String author, String question) {
		Statement statement = repo.save(new Statement(StatementType.QUESTION, author, State.UNRESOLVED, question));
		StatementLink statementLink = new StatementLink(statementId, LinkType.CHALLENGE, statement.getId());
		statementLink = statementLinkRepository.save(statementLink);
		
		return statement;
	}

	public Statement save(Statement statement) {
		return repo.save(statement);
	}

	public Optional<Statement> findById(Long id) {
		return repo.findById(id);
	}

	public long count() {
		return repo.count();
	}

	public List<Statement> findAllBySid(String sid) {
		return repo.findAllBySid(sid);
	}

	public List<Statement> findAllByAuthor(String author) {
		return repo.findAllByAuthor(author);
	}

	public Object findAll() {
		return repo.findAll();
	}
}
