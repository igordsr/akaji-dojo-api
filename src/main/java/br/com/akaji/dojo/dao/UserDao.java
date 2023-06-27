package br.com.akaji.dojo.dao;

import br.com.akaji.dojo.common.Constants;
import br.com.akaji.dojo.common.Functions;
import br.com.akaji.dojo.dtos.UserDTO;
import br.com.akaji.dojo.dtos.UserFormTempleteDto;
import br.com.akaji.dojo.exception.AKAJIException;
import br.com.akaji.dojo.interfaces.DataTransferObject;
import br.com.akaji.dojo.models.AcademicEducation;
import br.com.akaji.dojo.models.User;
import br.com.akaji.dojo.repositories.AcademicEducationService;
import br.com.akaji.dojo.repositories.AddressRepository;
import br.com.akaji.dojo.repositories.UserLoginRepository;
import br.com.akaji.dojo.repositories.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Log4j2
@Component
public class UserDao {
    @Autowired
    protected UserRepository userRepository;
    @Autowired
    protected UserLoginRepository userLoginRepository;
    @Autowired
    protected AddressRepository addressRepository;
    @Autowired
    protected AcademicEducationService academicEducationService;

    @Autowired
    protected MessageSource messageSource;

    public User create(Authentication authentication, UserDTO userDTO) throws Exception {
        User user = null;
        try {
            user = userDTO.map();
            if (this.checkUserAlreadyExists(user))
                this.throwExeceptionAlreadyExists(user.getName());

            user = this.userRepository.save(user);
        } catch (AKAJIException exception) {
            throw exception;
        } catch (JpaSystemException exception) {
            log.error(exception);
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
        }
        return user;
    }

    public DataTransferObject read(Authentication authentication, Long id) {
        DataTransferObject userDto = null;
        Optional<User> result = this.userRepository.findUserById(id);
        if (result.isPresent()) {
            userDto = result.get().toDto();
        }
        return userDto;
    }

    public DataTransferObject update(Authentication authentication, Long id, UserDTO vo) {
        DataTransferObject userDto = null;
        Optional<Integer> result = this.userRepository.findOptionalUserById(id);
        if (result.isPresent()) {
            try {
                userDto = vo.update(id, this.userRepository).toDto();
            } catch (JpaSystemException exception) {
                log.error("AKAJIInterceptor exception log ::".concat(exception.getMostSpecificCause().getMessage()),
                        exception);
            } catch (Exception exception) {
                log.error("AKAJIInterceptor exception log ::".concat(exception.getMessage()), exception);
            }
        }
        return userDto;
    }

    @Transactional
    public Boolean delete(Authentication authentication, Long id) {
        Boolean userDeleted = false;
        try {
            Optional<User> optionalUser = this.userRepository.findUserIdAndLoginById(id);
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                userDeleted = this.userRepository.deleteLogicalById(user.getId()) == 1 ? Boolean.TRUE : Boolean.FALSE;
                if (user.getUserLogin() != null) {
                    this.userLoginRepository.deleteById(user.getUserLogin().getUsername());
                }
            }
        } catch (Exception exception) {
            log.error("AKAJIInterceptor exception log ::".concat(exception.getMessage()), exception);
        }

        return userDeleted;
    }
    public Boolean updateStatus(Authentication authentication, Long id) {
        Boolean status = Boolean.FALSE;
        try {
            Optional<User> optionalUser = this.userRepository.findUserIdAndLoginById(id);
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                if (!user.getEnabled()) {
                    status = this.activate(authentication, user);
                } else {
                    status = this.inactivate(authentication, user);
                }
            }
        } catch (Exception exception) {
            log.error("AKAJIInterceptor exception log ::".concat(exception.getMessage()), exception);
        }
        return status;
    }

    protected Boolean inactivate(Authentication authentication, User user) {
        Boolean userInactivated = false;
        try {
            if (user == null || user.getEnabled() == null) {
                return userInactivated;
            }
            userInactivated = this.userRepository.inactivateUserById(user.getId()) == 1 ? Boolean.TRUE : Boolean.FALSE;
        } catch (Exception exception) {
            log.error("AKAJIInterceptor exception log ::".concat(exception.getMessage()), exception);
        }
        return userInactivated;
    }

    protected Boolean activate(Authentication authentication, User user) {
        Boolean userAactivated = false;
        try {
            if (user == null || user.getEnabled() == null) {
                return userAactivated;
            }
            userAactivated = this.userRepository.activateUserById(user.getId()) == 1 ? Boolean.TRUE : Boolean.FALSE;
        } catch (Exception exception) {
            log.error("AKAJIInterceptor exception log ::".concat(exception.getMessage()), exception);
        }
        return userAactivated;
    }

    public UserFormTempleteDto getUserFormTempleDto() {
        UserFormTempleteDto userFormTempleteDto = new UserFormTempleteDto();
        List<AcademicEducation> academicEducationList = this.academicEducationService.findAll();

        userFormTempleteDto.setAcademicEducationOptions(academicEducationList);
        return userFormTempleteDto;
    }

    public Boolean checkUserAlreadyExists(User user) {
        return this.checkUserAlreadyExists(user.getName(), user.getRg(), user.getCpf()).isPresent();
    }

    public Optional<Long> checkUserAlreadyExists(final String name, final String rg, final String cpf) {
        return this.userRepository.findOptionalByNameOrRgOrCpf(name, rg, cpf);
    }

    public void throwExeceptionAlreadyExists(String name) throws AKAJIException {
        Map<String, Object> objMessage = new HashMap<>();
        objMessage.put("register", name);
        String replace = Functions.getMessageFromProperties(messageSource, objMessage,
                Constants.AKAJI_BUSINESS_RULE_ERROR_HANDLER_REQUIRED_VALIDATION_DUPLICATE_RECORD_ON_DATABASE);
        throw new AKAJIException(
                Constants.AKAJI_BUSINESS_RULE_ERROR_HANDLER_REQUIRED_VALIDATION_DUPLICATE_RECORD_ON_DATABASE,
                Constants.BUSINESS_RULES, getClass().getName(), replace);
    }

    public void throwExeceptionNotExists(String name) throws AKAJIException {
        Map<String, Object> objMessage = new HashMap<>();
        objMessage.put("register", name);
        String replace = Functions.getMessageFromProperties(messageSource, objMessage, Constants.AKAJI_ERROR_HANDLER_USER_NOT_FOUND);
        throw new AKAJIException(
                Constants.AKAJI_BUSINESS_RULE_ERROR_HANDLER_REQUIRED_VALIDATION_DUPLICATE_RECORD_ON_DATABASE,
                Constants.BUSINESS_RULES, getClass().getName(), replace);
    }
}
