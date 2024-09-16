package br.com.mickaelsantos.api_cursos.modules.student.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.mickaelsantos.api_cursos.modules.student.dto.DeleteStudentResponseDto;
import br.com.mickaelsantos.api_cursos.modules.student.dto.StudentResponseDto;
import br.com.mickaelsantos.api_cursos.modules.student.dto.ToggleStudentResponseDto;
import br.com.mickaelsantos.api_cursos.modules.student.models.Student;
import br.com.mickaelsantos.api_cursos.modules.student.usecases.CreateStudentUseCase;
import br.com.mickaelsantos.api_cursos.modules.student.usecases.DeleteStudentUseCase;
import br.com.mickaelsantos.api_cursos.modules.student.usecases.ListStudentUseCase;
import br.com.mickaelsantos.api_cursos.modules.student.usecases.ToggleStudentUseCase;
import br.com.mickaelsantos.api_cursos.modules.student.usecases.UpdateStudentUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/student")
public class StudentController 
{
    @Autowired
    private CreateStudentUseCase createStudentUseCase;
    @Autowired
    private UpdateStudentUseCase updateStudentUseCase;
    @Autowired
    private ListStudentUseCase listStudentUseCase;
    @Autowired
    private DeleteStudentUseCase deleteStudentUseCase;
    @Autowired
    private ToggleStudentUseCase toggleStudentUseCase;

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
            .active(result.isActive())
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

    @PutMapping("/update")
    @PreAuthorize("hasRole('STUDENT')")

    public ResponseEntity<Object> update(@Valid @RequestBody Student student, HttpServletRequest request)
    {
        try
        {
           var studentUuId = UUID.fromString(request.getAttribute("student_uuid").toString());
           var result = updateStudentUseCase.execute(studentUuId, student);
           var studentDTO = StudentResponseDto.builder()
           .uuId(result.getUuId())
           .name(result.getName())
           .cpf(result.getCpf())
           .email(result.getEmail())
           .username(result.getUsername())
           .password(result.getPassword())
           .active(result.isActive())
           .created_at(result.getCreated_at())
           .updated_at(result.getUpdated_at())
           .build();
           
           return ResponseEntity.ok().body(studentDTO);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return ResponseEntity.ok().body(ex.getMessage());
        }
    }

    @GetMapping("/get")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<Object> get()
    {
        try
        {
            List<StudentResponseDto> studentsDTO = new ArrayList<>();
            var students = listStudentUseCase.execute();
            for (Student student : students) 
            {
                var studentDTO = StudentResponseDto.builder()
                .uuId(student.getUuId())
                .name(student.getName())
                .cpf(student.getCpf())
                .email(student.getEmail())
                .username(student.getUsername())
                .password(student.getPassword())
                .active(student.isActive())
                .created_at(student.getCreated_at())
                .updated_at(student.getUpdated_at())
                .build();

                studentsDTO.add(studentDTO);
            }
            return ResponseEntity.ok().body(studentsDTO);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
       
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<Object> delete(HttpServletRequest request)
    {
        try
        {
            var studentUuId = UUID.fromString(request.getAttribute("student_uuid").toString());
            deleteStudentUseCase.execute(studentUuId); 
            var resultOfDelete = DeleteStudentResponseDto.builder()
            .message("Estudante deletado com sucesso!")
            .isDeleted(true)
            .build();

            return ResponseEntity.ok().body(resultOfDelete);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            var resultOfDelete = DeleteStudentResponseDto.builder()
            .message("Ocorreu um problema ao tentar deletar o estudante")
            .isDeleted(false)
            .build();
            return ResponseEntity.badRequest().body(resultOfDelete + " " + ex.getMessage());
        }
        
    }

    @PatchMapping("/toggle")
    @PreAuthorize("hasRole('STUDENT')")

    public ResponseEntity<Object> toggle(@RequestParam boolean status, HttpServletRequest request)
    {
        try
        {
            var studentUuId = UUID.fromString(request.getAttribute("student_uuid").toString());
            var student = toggleStudentUseCase.execute(studentUuId, status);
            var studentDTO = new ToggleStudentResponseDto(
                student.getName(),
                student.isActive());

            return ResponseEntity.ok().body(studentDTO);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
        

    }

    
}

