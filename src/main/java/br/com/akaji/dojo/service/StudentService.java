package br.com.akaji.dojo.service;

import br.com.akaji.dojo.dao.StudentDao;
import br.com.akaji.dojo.dtos.StudentDTO;
import br.com.akaji.dojo.dtos.StudentFormTempleteDto;
import br.com.akaji.dojo.interfaces.CrudAkaji;
import br.com.akaji.dojo.interfaces.DataTransferObject;
import br.com.akaji.dojo.utils.LogInfo;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
public class StudentService implements CrudAkaji<StudentDTO> {
    @Autowired
    private StudentDao studentDao;

    public StudentFormTempleteDto getStudentFormTempleDto() {
        return this.studentDao.getStudentFormTempleDto();
    }

    public boolean updateEnabledStatus(Authentication authentication, Long id) {
        return this.studentDao.updateStatus(authentication, id);
    }


    @Override
    public DataTransferObject read(Authentication authentication, Long id) {
        final LogInfo logger = new LogInfo(StudentService.class, "read").append("id", id).startLog();
        DataTransferObject read = this.studentDao.read(authentication, id);
        logger.finishLog(read);
        return read;
    }

    @Override
    public List<DataTransferObject> listAll(Authentication authentication) {
        return this.studentDao.listAll(authentication);
    }

    @Override
    public Page<DataTransferObject> listAll(Authentication authentication, Pageable pageable) {
        return this.studentDao.listAll(authentication, pageable);
    }

    @Override
    public DataTransferObject create(Authentication authentication, StudentDTO studentDTO) throws Exception {
        return this.studentDao.create(authentication, studentDTO);
    }

    @Override
    public DataTransferObject update(Authentication authentication, Long id, StudentDTO vo) {
        return this.studentDao.update(authentication, id, vo);
    }

    @Override
    public Boolean delete(Authentication authentication, Long id) {
        return this.studentDao.delete(authentication, id);
    }

}
