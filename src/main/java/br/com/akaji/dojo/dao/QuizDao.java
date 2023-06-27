package br.com.akaji.dojo.dao;

import java.util.List;
import java.util.Optional;

import br.com.akaji.dojo.dtos.QuizDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import br.com.akaji.dojo.interfaces.Cast;
import br.com.akaji.dojo.interfaces.CrudAkaji;
import br.com.akaji.dojo.interfaces.DataTransferObject;
import br.com.akaji.dojo.models.Quiz;
import br.com.akaji.dojo.models.Test;
import br.com.akaji.dojo.repositories.QuizRepository;

@Component
public class QuizDao implements CrudAkaji<QuizDTO> {
	@Autowired
	private QuizRepository quizRepository;

	public DataTransferObject getHealthInfo(Authentication authentication, Long id) {
		DataTransferObject testDto = null;
		Optional<Cast> healthInfo = this.quizRepository.getHealthInfo(id);
		if (healthInfo.isPresent()) {
			testDto = healthInfo.get().toDto();
		} else {
			testDto = this.getNewHealthInfo(authentication);
		}
		return testDto;
	}

	public DataTransferObject getNewHealthInfo(Authentication authentication) {
		DataTransferObject quizDto = null;
		Optional<List<Quiz>> healthInfo = this.quizRepository.getTestHealthInfo();

		if (healthInfo.isPresent() && healthInfo.get().size() > 0) {
			int size = healthInfo.get().size();
			Quiz quiz = healthInfo.get().get(size - 1);
			quizDto = new Test(quiz.getTest().getId(), quiz.getTest().getDescription(), healthInfo.get()).toDto();
		}
		return quizDto;
	}

	@Override
	public DataTransferObject read(Authentication authentication, Long id) {
		// TODO Auto-generated method stub
		return null;
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
	public DataTransferObject create(Authentication authentication, QuizDTO obj) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataTransferObject update(Authentication authentication, Long id, QuizDTO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean delete(Authentication authentication, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
