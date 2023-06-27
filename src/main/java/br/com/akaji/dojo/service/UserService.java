package br.com.akaji.dojo.service;

import java.util.List;
import java.util.Optional;

import br.com.akaji.dojo.dtos.UserDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.akaji.dojo.dao.UserDao;
import br.com.akaji.dojo.dtos.UserFormTempleteDto;
import br.com.akaji.dojo.interfaces.CrudAkaji;
import br.com.akaji.dojo.interfaces.DataTransferObject;
import br.com.akaji.dojo.models.User;
@Log4j2
@Service
public class UserService implements CrudAkaji<UserDTO> {
	@Autowired
	private UserDao userDao;

	public UserFormTempleteDto getUserFormTempleDto() {
		return this.userDao.getUserFormTempleDto();
	}

	@Override
	public DataTransferObject read(Authentication authentication, Long id) {
		log.info(String.format("Class %s :: Method %s", StudentService.class, "readALLDetails"));
		return this.userDao.read(authentication, id);
	}

	@Override
	public List<DataTransferObject> listAll(Authentication authentication) {
		return null;
	}

	@Override
	public Page<DataTransferObject> listAll(Authentication authentication, Pageable pageable) {
		return null;
	}

	@Override
	public DataTransferObject create(Authentication authentication, UserDTO obj) throws Exception {
		return null;
	}

	@Override
	public DataTransferObject update(Authentication authentication, Long id, UserDTO userDTO) {
		log.info(String.format("Class %s :: Method %s", UserService.class, "update"));
		return this.userDao.update(authentication, id, userDTO);
	}

	@Override
	public Boolean delete(Authentication authentication, Long id) {
		return null;
	}

	public Boolean checkStudentAlreadyExists(User user){
		Boolean isPresent = Boolean.FALSE;
		Optional<Long> checkUserAlreadyExists = this.userDao.checkUserAlreadyExists(user.getName(), user.getRg(), user.getCpf());
		isPresent = checkUserAlreadyExists.isPresent();
		if(isPresent) {
			user.setId(checkUserAlreadyExists.get());
		}
		return isPresent;
	}

}
