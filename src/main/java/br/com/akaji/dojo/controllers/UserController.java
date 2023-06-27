package br.com.akaji.dojo.controllers;

import br.com.akaji.dojo.dtos.PageableDTO;
import br.com.akaji.dojo.dtos.UserDTO;
import br.com.akaji.dojo.dtos.UserFormTempleteDto;
import br.com.akaji.dojo.exception.AKAJIException;
import br.com.akaji.dojo.interfaces.DataTransferObject;
import br.com.akaji.dojo.models.User;
import br.com.akaji.dojo.security.UserLogin;
import br.com.akaji.dojo.service.UserService;
import br.com.akaji.dojo.dtos.DocumentInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/users")
class UserController {
	@Autowired
	private UserService userService;

	@ResponseBody
	@GetMapping("/screenSettings")
	public ResponseEntity<UserFormTempleteDto> getScreenSettings() {
		ResponseEntity<UserFormTempleteDto> response = ResponseEntity.notFound().build();
		UserFormTempleteDto userFormTempleDto = this.userService.getUserFormTempleDto();
		if (userFormTempleDto != null) {
			response = ResponseEntity.ok(userFormTempleDto);
		}
		return response;
	}

	@ResponseBody
	@GetMapping()
	public ResponseEntity<PageableDTO> listAll(@RequestParam Integer page, @RequestParam Integer size) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		PageableDTO pageableDTO = PageableDTO.getNewInstancePageableDTO(page, size);

		Pageable pageable = PageRequest.of(pageableDTO.getPage(), pageableDTO.getSize());
		PageableDTO pageableDto = new PageableDTO(this.userService.listAll(authentication, pageable));
		return ResponseEntity.ok(pageableDto);
	}

	
	@ResponseBody
	@GetMapping("/profiles")
	public ResponseEntity<DataTransferObject> getUserProfile() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserLogin userLogin = (UserLogin) authentication.getPrincipal();
		ResponseEntity<DataTransferObject> response = ResponseEntity.notFound().build();
		DataTransferObject userDto = this.userService.read(authentication, userLogin.getUser().getId());
		if (userDto != null) {
			response = ResponseEntity.ok(userDto);
		}
		return response;
	}
	
	@ResponseBody
	@GetMapping("/{id}")
	public ResponseEntity<DataTransferObject> readALLDetails(@PathVariable Long id) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		ResponseEntity<DataTransferObject> response = ResponseEntity.notFound().build();
		DataTransferObject userDto = this.userService.read(authentication, id);
		if (userDto != null) {
			response = ResponseEntity.ok(userDto);
		}
		return response;
	}

	@Transactional
	@PostMapping()
	public ResponseEntity<DataTransferObject> create(@RequestBody @Valid UserDTO userDTO,
			UriComponentsBuilder uriBuilder) throws Exception {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		DataTransferObject userDto = this.userService.create(authentication, userDTO);
		URI uri = uriBuilder.path("/user/{id}").buildAndExpand(userDto.getId()).toUri();
		return ResponseEntity.created(uri).body(userDto);

	}

	@Transactional
	@PutMapping("/{id}")
	public ResponseEntity<DataTransferObject> update(@PathVariable Long id, @RequestBody @Valid UserDTO userDTO, UriComponentsBuilder uriBuilder) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		DataTransferObject userDto = this.userService.update(authentication, id, userDTO);
		if (userDto != null) {
			URI uri = uriBuilder.path("/user/{id}").buildAndExpand(userDto.getId()).toUri();
			return ResponseEntity.created(uri).body(userDto);
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		ResponseEntity<Object> build = ResponseEntity.notFound().build();
		if (this.userService.delete(authentication, id)) {
			build = ResponseEntity.ok().build();
		}
		return build;
	}
	
	@ResponseBody
	@PostMapping("checkUser")
	public ResponseEntity<DataTransferObject> checkStudentAlreadyExists(@RequestBody @Valid DocumentInfoDTO documentInfoDTO, UriComponentsBuilder uriBuilder) throws AKAJIException {
		User user = new User(documentInfoDTO);
		this.userService.checkStudentAlreadyExists(user);
		DataTransferObject userDto = user.toDto();
		return ResponseEntity.ok(userDto);
	}

}
