package br.com.akaji.dojo.controllers;

import br.com.akaji.dojo.dtos.PageableDTO;
import br.com.akaji.dojo.dtos.StudentDTO;
import br.com.akaji.dojo.dtos.StudentFormTempleteDto;
import br.com.akaji.dojo.interfaces.DataTransferObject;
import br.com.akaji.dojo.service.StudentService;
import br.com.akaji.dojo.utils.LogInfo;
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
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @Transactional
    @PostMapping()
    public ResponseEntity<DataTransferObject> create(@RequestBody @Valid StudentDTO studentDTO, UriComponentsBuilder uriBuilder) throws Exception {
        final LogInfo logger = new LogInfo(StudentController.class, "create").append("studentDTO", studentDTO).startLog();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        DataTransferObject studentDto = this.studentService.create(authentication, studentDTO);
        URI uri = uriBuilder.path("/user/{id}").buildAndExpand(studentDto.getId()).toUri();
        logger.finishLog(studentDto);
        return ResponseEntity.created(uri).body(studentDto);
    }

    @ResponseBody
    @GetMapping("/{id}")
    public ResponseEntity<DataTransferObject> read(@PathVariable Long id) {
        final LogInfo logger = new LogInfo(StudentController.class, "read").append("id", id).startLog();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ResponseEntity<DataTransferObject> response = ResponseEntity.notFound().build();
        DataTransferObject userDto = this.studentService.read(authentication, id);
        if (userDto != null)
            response = ResponseEntity.ok(userDto);

        logger.finishLog(response);
        return response;
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<DataTransferObject> update(@PathVariable Long id, @RequestBody @Valid StudentDTO studentDTO, UriComponentsBuilder uriBuilder) {
        final LogInfo logger = new LogInfo(StudentController.class, "update").append("id", id).append("studentDTO", studentDTO).startLog();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        DataTransferObject studentDto = this.studentService.update(authentication, id, studentDTO);
        if (studentDto != null) {
            URI uri = uriBuilder.path("/user/{id}").buildAndExpand(studentDto.getId()).toUri();
            logger.finishLog(studentDto);
            return ResponseEntity.created(uri).body(studentDto);
        }
        logger.finishLog();
        return ResponseEntity.notFound().build();
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        final LogInfo logger = new LogInfo(StudentController.class, "delete").append("id", id).startLog();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ResponseEntity<Object> build = ResponseEntity.notFound().build();
        if (this.studentService.delete(authentication, id))
            build = ResponseEntity.ok().build();
        logger.finishLog();
        return build;
    }

    @ResponseBody
    @GetMapping()
    public ResponseEntity<PageableDTO> list(@RequestParam Integer page, @RequestParam Integer size) {
        final LogInfo logger = new LogInfo(StudentController.class, "list").append("page", page).append("size", size).startLog();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PageableDTO pageableDTO = PageableDTO.getNewInstancePageableDTO(page, size);
        Pageable pageable = PageRequest.of(pageableDTO.getPage(), pageableDTO.getSize());
        PageableDTO pageableDto = new PageableDTO(this.studentService.listAll(authentication, pageable));
        logger.finishLog(pageableDto);
        return ResponseEntity.ok(pageableDto);
    }

    @ResponseBody
    @GetMapping("/screenSettings")
    public ResponseEntity<StudentFormTempleteDto> getScreenSettings() {
        ResponseEntity<StudentFormTempleteDto> response = ResponseEntity.notFound().build();
        StudentFormTempleteDto studentFormTempleDto = this.studentService.getStudentFormTempleDto();
        if (studentFormTempleDto != null)
            response = ResponseEntity.ok(studentFormTempleDto);
        return response;
    }


    @Transactional
    @PatchMapping("/{id}/status")
    public ResponseEntity<?> updateStatus(@PathVariable Long id) {
        final LogInfo logger = new LogInfo(StudentController.class, "updateStatus").append("id", id).startLog();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean result = this.studentService.updateEnabledStatus(authentication, id);
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(id);
        studentDTO.setEnabled(result);
        logger.finishLog(studentDTO);
        return ResponseEntity.ok(studentDTO);
    }

}
