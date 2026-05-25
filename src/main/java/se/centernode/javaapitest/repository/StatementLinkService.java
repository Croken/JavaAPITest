package se.centernode.javaapitest.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import se.centernode.javaapitest.entity.StatementLink;

@Service
@AllArgsConstructor
public class StatementLinkService {

	private StatementLinkRepository repo;

	public StatementLink save(StatementLink source) {
		return repo.save(source);
	}

	public List<StatementLink> findAll() {
		return repo.findAll();
	}

	public void delete(StatementLink save) {
		repo.delete(save);
	}

	public Optional<StatementLink> findById(Long id) {
		return repo.findById(id);
	}

	public List<StatementLink> findAllBySource(long id) {
		return repo.findAllBySource(id);
	}

	public List<StatementLink> findAllByPointer(long id) {
		return repo.findAllByPointer(id);
	}
}

