package br.com.akaji.dojo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.akaji.dojo.models.Gym;

@Repository
public interface GymRepository extends JpaRepository<Gym, Long> {


	@Query("SELECT new Gym(	g.id, g.name, u.name, u.mainEmail, u.secondaryEmail, u.cellPhone, u.landline, a.zipCode, a.logradouro, a.number, a.complement, a.referencePoint, a.neighborhood, a.city, a.estate) "
			+ "FROM User u "
			+ "JOIN ChiefExecutiveOfficer c ON (c.id = u.id) "
			+ "JOIN Gym g ON (c.id = g.ceo) "
			+ "JOIN Address a ON (g.headOfficeAddress = a.id) "
			+ "WHERE (:id IS NULL OR g.id = :id) AND g.deleted = false")
	public Optional<Gym> findById(@Param("id") Long id);

}
