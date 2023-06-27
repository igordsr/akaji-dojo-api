package br.com.akaji.dojo.service;

import java.util.List;

import br.com.akaji.dojo.dtos.QuizDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.akaji.dojo.dao.QuizDao;
import br.com.akaji.dojo.interfaces.CrudAkaji;
import br.com.akaji.dojo.interfaces.DataTransferObject;

@Service
public class QuizService implements CrudAkaji<QuizDTO> {
    @Autowired
    private QuizDao quizDao;

    public DataTransferObject getHealthInfo(Authentication authentication, Long id) {
        return this.quizDao.getHealthInfo(authentication, id);
    }

    @Override
    public DataTransferObject read(Authentication authentication, Long id) {
        return null;
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
    public DataTransferObject create(Authentication authentication, QuizDTO obj) throws Exception {
        return null;
    }

    @Override
    public DataTransferObject update(Authentication authentication, Long id, QuizDTO vo) {
        return null;
    }

    @Override
    public Boolean delete(Authentication authentication, Long id) {
        return null;
    }
}
