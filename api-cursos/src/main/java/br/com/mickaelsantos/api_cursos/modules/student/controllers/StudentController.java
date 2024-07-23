package br.com.mickaelsantos.api_cursos.modules.student.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mickaelsantos.api_cursos.modules.student.dto.StudentResponseDto;
import br.com.mickaelsantos.api_cursos.modules.student.models.Student;
import br.com.mickaelsantos.api_cursos.modules.student.usecases.CreateStudentUseCase;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/student")
public class StudentController 
{
    @Autowired
    private CreateStudentUseCase createStudentUseCase;

    @PostMapping("/create")
    public ResponseEntity<Object> create(@Valid @RequestBody Student student)
    {
        try
        {
            var result = createStudentUseCase.execute(student);

            var studentDto = StudentResponseDto.builder()
            .uuId(result.getUuId())
            .name(result.getName())
            .cpf(result.getCpf())
            .email(result.getEmail())
            .password(result.getPassword())
            .created_at(result.getCreated_at())
            .updated_at(result.getUpdated_at())
            .build();

            return ResponseEntity.ok().body(studentDto);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
