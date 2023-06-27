package br.com.akaji.dojo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.akaji.dojo.interfaces.Cast;
import br.com.akaji.dojo.models.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
	@Query("SELECT 1 FROM Student s JOIN User u ON (s.id = u.id) WHERE (:ra IS NULL OR s.ra = :ra) AND (:id IS NULL OR s.id = :id)")
	Optional<Integer> findOptionalByRaORIdOrUserName(@Param("ra") Long ra, @Param("id") Long id);

	@Query("SELECT u.id, s.ra, s.startDate, u.name FROM Student s JOIN User u ON (s.id = u.id) WHERE (:id IS NULL OR s.id <> :id)")
	public List<Student> listAll(@Param("id") Long id);
	 
	@Query("SELECT new Student(u.id, s.ra, s.startDate, u.name, u.enabled) FROM Student s JOIN User u ON (s.id = u.id) WHERE (:id IS NULL OR s.id <> :id) AND u.deleted = false")
	public Page<Cast> listAll(@Param("id") Long id, Pageable pageable);
	
	@Modifying
	@Query("UPDATE Student s SET s.ra = :ra WHERE (:ra IS NOT NULL AND s.ra IS NULL) AND (:id IS NOT NULL AND s.id = :id)")
	public int UpdateRaById(@Param("id") Long id, @Param("ra") Long ra);	
}
