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
import br.com.akaji.dojo.service.GymService;

@RestController
@RequestMapping("/gyms")
public class GymController {

	@Autowired	
	private GymService gymService;
	
	@ResponseBody
	@GetMapping("/{id}")
	public ResponseEntity<DataTransferObject> readALLDetails(@PathVariable Long id) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		ResponseEntity<DataTransferObject> response = ResponseEntity.notFound().build();
		DataTransferObject dto = this.gymService.read(authentication, id);
		if (dto != null) {
			response = ResponseEntity.ok(dto);
		}
		return response;
	}
}
