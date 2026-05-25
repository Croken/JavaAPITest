package se.centernode.javaapitest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import se.centernode.javaapitest.entity.StatementLink;

@Repository
interface StatementLinkRepository extends JpaRepository<StatementLink, Long> {

	// Find by StatementIdSource
	@Query(value = "SELECT l FROM StatementLink l WHERE l.statementIdSource = :statementIdSource", nativeQuery = false)
	List<StatementLink> findAllBySource(Long statementIdSource);

	//Find by StatementIdPointer
	@Query(value = "SELECT l FROM StatementLink l WHERE l.statementIdPointer = :statementIdPointer", nativeQuery = false)
	List<StatementLink> findAllByPointer(Long statementIdPointer);
}
