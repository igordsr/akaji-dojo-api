package br.com.akaji.dojo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import br.com.akaji.dojo.dtos.GymDTO;
import br.com.akaji.dojo.interfaces.CrudAkaji;
import br.com.akaji.dojo.interfaces.DataTransferObject;
import br.com.akaji.dojo.models.Gym;
import br.com.akaji.dojo.repositories.GymRepository;

@Component
public class GymDao implements CrudAkaji<GymDTO>{

	@Autowired
	private GymRepository gymRepository;

	@Override
	public DataTransferObject read(Authentication authentication, Long id) {
		DataTransferObject gymDto = null;
		Optional<Gym> result = this.gymRepository.findById(id);
		if (result.isPresent()) {
			gymDto = result.get().toDto();
		}
		return gymDto;
	}

	@Override
	public List<DataTransferObject> listAll(Authentication authentication) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<DataTransferObject> listAll(Authentication authentication, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataTransferObject create(Authentication authentication, GymDTO obj) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataTransferObject update(Authentication authentication, Long id, GymDTO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean delete(Authentication authentication, Long id) {
		// TODO Auto-generated method stub
		return null;
	}
}
