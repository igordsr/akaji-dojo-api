package br.com.akaji.dojo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.akaji.dojo.dao.GymDao;
import br.com.akaji.dojo.dtos.GymDTO;
import br.com.akaji.dojo.interfaces.CrudAkaji;
import br.com.akaji.dojo.interfaces.DataTransferObject;

@Service
public class GymService implements CrudAkaji<GymDTO>{
	@Autowired
	private GymDao gymDao;

	@Override
	public DataTransferObject read(Authentication authentication, Long id) {
		return this.gymDao.read(authentication, id);
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
