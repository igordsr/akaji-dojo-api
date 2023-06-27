package br.com.akaji.dojo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.akaji.dojo.security.UserLogin;

@Repository
public interface UserLoginRepository extends JpaRepository<UserLogin, String> {

	Optional<UserLogin> findByUserName(String userName);

	@Query("SELECT new UserLogin(u.id as id, u.name as name, ul) "
			+ "FROM User u INNER JOIN UserLogin ul ON (u.userLogin = ul.user)"
			+ "WHERE (:userId IS NULL OR UPPER(TRIM(u.rg)) = UPPER(TRIM(:userId))  ) OR "
			+ "(:userId IS NULL OR UPPER(TRIM(u.mainEmail)) = UPPER(TRIM(:userId)) ) OR "
			+ "(:userId IS NULL OR UPPER(TRIM(ul.userName)) = UPPER(TRIM(:userId)) )")
	Optional<UserLogin> getUserLogin(@Param("userId") String userId);
}
