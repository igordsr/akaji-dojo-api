package br.com.akaji.dojo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.akaji.dojo.interfaces.Cast;
import br.com.akaji.dojo.models.Quiz;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {

	@Query("SELECT q FROM " + "Test t INNER JOIN Quiz q ON(t.id = q.test) " + "INNER JOIN Response r ON(q.id = r.quiz) "
			+ "INNER JOIN HealthInfo h ON(r.healthInfo = h.id) " + "INNER JOIN Student s ON(h.student = s.id)"
			+ "WHERE t.id = 1 AND (:id IS NULL AND s.id = :id)")
	public Optional<Cast> getHealthInfo(@Param("id") Long id);

	@Query("SELECT q FROM Test t INNER JOIN Quiz q ON(t.id = q.test AND t.id = 1)")
	public Optional<List<Quiz>> getTestHealthInfo();

	@Query("SELECT q FROM Test t INNER JOIN Quiz q ON(t.id = q.test AND t.id = 1)")
	public Optional<List<Quiz>> getQuestions();
}
