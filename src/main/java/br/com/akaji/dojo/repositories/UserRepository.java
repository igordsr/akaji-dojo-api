package br.com.akaji.dojo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import br.com.akaji.dojo.models.User;

public interface UserRepository extends JpaRepository<User, Long> {

	@Query("SELECT 1 FROM User u WHERE (:name IS NULL OR u.name LIKE :name) OR (:rg IS NULL OR u.rg LIKE :rg)")
	Optional<User> findOptionalByNameOrRg(@Param("name") String name, @Param("rg") String rg);
	
	@Query("SELECT u.id FROM User u WHERE (:name IS NOT NULL AND u.name LIKE :name) OR (:rg IS NOT NULL AND u.rg LIKE :rg) OR (:cpf IS NOT NULL AND u.cpf LIKE :cpf)")
	Optional<Long> findOptionalByNameOrRgOrCpf(@Param("name") String name, @Param("rg") String rg,  @Param("cpf") String cpf);
	
	@Query("SELECT new User(u.id, u.enabled, l) FROM User u LEFT JOIN UserLogin l ON (u.userLogin = l.user) WHERE (:id IS NULL OR u.id = :id)")
	Optional<User> findUserIdAndLoginById(@Param("id") Long id);
	
	@Query("SELECT new User(u.id, u.name, u.socialName, u.rg, u.cpf, u.birthDate, u.gender, u.mother, u.father, u.mainEmail, u.secondaryEmail, u.cellPhone, u.landline, u.emergencyContact, u.academicEducation, u.academicEducationStatus, u.profession, u.created, u.updated, "
			+ "a.id, a.zipCode, a.logradouro, a.number, a.complement, a.referencePoint, a.neighborhood, a.city, a.estate, a.created, a.updated) "
			+ "FROM User u INNER JOIN Address a ON (u.address = a.id) "
			+ "WHERE (:id IS NULL OR u.id = :id)")
	Optional<User> findUserById(@Param("id") Long id);
	
	@Query("SELECT 1 FROM User u WHERE (:id IS NULL OR u.id = :id)")
	Optional<Integer> findOptionalUserById(@Param("id") Long id);
	
	@Modifying
	@Transactional
	@Query("UPDATE User SET deleted = true WHERE:id IS NULL OR id = :id")
	public Integer deleteLogicalById(@Param("id") Long id);
	
	@Modifying
	@Transactional
	@Query("UPDATE User u "
			+ "SET u.enabled = false "
			+ "WHERE (:id IS NULL OR u.id = :id)")
	public Integer inactivateUserById(@Param("id") Long id);

	@Modifying
	@Transactional
	@Query("UPDATE User u "
			+ "SET u.enabled = true "
			+ "WHERE (:id IS NULL OR u.id = :id)")
	public Integer activateUserById(Long id);
	
}
