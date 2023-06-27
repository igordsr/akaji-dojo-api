package br.com.akaji.dojo.dao;

import br.com.akaji.dojo.common.Constants;
import br.com.akaji.dojo.common.Functions;
import br.com.akaji.dojo.dtos.StudentDTO;
import br.com.akaji.dojo.dtos.StudentFormTempleteDto;
import br.com.akaji.dojo.dtos.UserFormTempleteDto;
import br.com.akaji.dojo.exception.AKAJIException;
import br.com.akaji.dojo.interfaces.Cast;
import br.com.akaji.dojo.interfaces.CrudAkaji;
import br.com.akaji.dojo.interfaces.DataTransferObject;
import br.com.akaji.dojo.models.Student;
import br.com.akaji.dojo.models.User;
import br.com.akaji.dojo.repositories.StudentRepository;
import br.com.akaji.dojo.security.UserLogin;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import static br.com.akaji.dojo.common.Constants.AKAJI_INTERCEPTOR_EXCEPTION_LOG;
import static java.util.Calendar.*;

@Log4j2
@Component
public class StudentDao extends UserDao implements CrudAkaji<StudentDTO> {
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public DataTransferObject create(Authentication authentication, StudentDTO studentDTO) throws Exception {
        DataTransferObject dao = new StudentDTO();
        try {
            final Calendar TODAY = Functions.TODAY();
            UserLogin userLogin = (UserLogin) authentication.getPrincipal();

            User userManaged = super.create(authentication, studentDTO);

            String sequence = Functions.generateSequence(TODAY.get(YEAR), TODAY.get(MONTH), TODAY.get(DAY_OF_MONTH), ThreadLocalRandom.current().nextInt(10, 99),
                    userLogin.getUser().getId(), Constants.UF_CODE_SP, userManaged.getBirthDate().get(YEAR), userManaged.getId());
            studentDTO.setRa(Long.parseLong(sequence));
            this.studentRepository.UpdateRaById(userManaged.getId(), studentDTO.getRa());
            return studentDTO.map(new Student(userManaged)).toDto();
        } catch (AKAJIException exception) {
            throw exception;
        } catch (JpaSystemException exception) {
            log.error(exception);
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
        }
        return dao;
    }

    @Override
    public DataTransferObject read(Authentication authentication, Long id) {
        DataTransferObject studentDto = null;
        Optional<Student> result = this.studentRepository.findById(id);
        if (result.isPresent()) {
            studentDto = result.get().toDto();
        }
        return studentDto;
    }

    @Override
    public DataTransferObject update(Authentication authentication, Long id, StudentDTO studentDTO) {
        DataTransferObject studentManaged = null;
        try {
            Optional<Student> studentOptional = this.studentRepository.findById(id);
            if (!studentOptional.isPresent()) {
                super.throwExeceptionNotExists(studentDTO.getName());
            }
            Student student = studentOptional.get();
            studentDTO.map(student);
            studentManaged = student.toDto();
        } catch (JpaSystemException exception) {
            log.error(AKAJI_INTERCEPTOR_EXCEPTION_LOG.concat(exception.getMostSpecificCause().getMessage()), exception);
        } catch (Exception exception) {
            log.error(AKAJI_INTERCEPTOR_EXCEPTION_LOG.concat(exception.getMessage()), exception);
        }
        return studentManaged;
    }

    @Override
    public Boolean delete(Authentication authentication, Long id) {
        return super.delete(authentication, id);
    }

    @Override
    public List<DataTransferObject> listAll(Authentication authentication) {
        UserLogin userLogin = (UserLogin) authentication.getPrincipal();
        List<DataTransferObject> studentDto = new ArrayList<>();
        try {
            List<Student> students = this.studentRepository.listAll(userLogin.getUser().getId());
            if (students != null) {
                studentDto = students.stream().map(Student::toDto).collect(Collectors.toList());
            }
        } catch (Exception exception) {
            log.error(AKAJI_INTERCEPTOR_EXCEPTION_LOG.concat(exception.getMessage()), exception);
        }
        return studentDto;
    }

    @Override
    public Page<DataTransferObject> listAll(Authentication authentication, Pageable pageable) {
        Page<DataTransferObject> students = null;
        UserLogin userLogin = (UserLogin) authentication.getPrincipal();
        try {
            students = this.studentRepository.listAll(userLogin.getUser().getId(), pageable).map(Cast::toDto);
        } catch (Exception exception) {
            log.error(AKAJI_INTERCEPTOR_EXCEPTION_LOG.concat(exception.getMessage()), exception);
        }
        return students;
    }

    public StudentFormTempleteDto getStudentFormTempleDto() {
        UserFormTempleteDto userFormTempleDto = super.getUserFormTempleDto();
        StudentFormTempleteDto studentFormTempleteDto = new StudentFormTempleteDto(userFormTempleDto);
        return studentFormTempleteDto;
    }

}
