package br.com.akaji.dojo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.akaji.dojo.models.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
	
}
