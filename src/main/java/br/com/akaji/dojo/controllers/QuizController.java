package br.com.akaji.dojo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.akaji.dojo.interfaces.DataTransferObject;
import br.com.akaji.dojo.service.QuizService;

@RestController
@RequestMapping("/quiz")
public class QuizController {
	
	@Autowired
	private QuizService quizService;

	
	@ResponseBody
	@GetMapping("/{id}/healthInfo")
	public ResponseEntity<DataTransferObject> getHealthInfo(@PathVariable Long id) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		ResponseEntity<DataTransferObject> response = ResponseEntity.notFound().build();
		DataTransferObject quizDto = this.quizService.getHealthInfo(authentication, id);
		if (quizDto != null) {
			response = ResponseEntity.ok(quizDto);
		}
		return response;
	}
}
