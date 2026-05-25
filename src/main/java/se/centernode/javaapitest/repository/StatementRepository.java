package se.centernode.javaapitest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import se.centernode.javaapitest.entity.Statement;

@Repository
interface StatementRepository extends JpaRepository<Statement, Long> {

	// Find all by SID order by version, latest first
	@Query(value = "SELECT s FROM Statement s WHERE s.sid = :sid ORDER BY s.version DESC", nativeQuery = false)
	List<Statement> findAllBySid(String sid);

	// Find all by author, order by created_time, latest first:  ORDER BY s.created_time DESC
	@Query(value = "SELECT s FROM Statement s WHERE s.author = :author ORDER BY s.version DESC", nativeQuery = false)
	List<Statement> findAllByAuthor(String author);

	//	Find all by author, order by created_time, latest first only latest version?
}
