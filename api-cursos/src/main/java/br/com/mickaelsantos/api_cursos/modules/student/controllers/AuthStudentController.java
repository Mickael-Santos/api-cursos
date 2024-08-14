package br.com.mickaelsantos.api_cursos.modules.student.controllers;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mickaelsantos.api_cursos.modules.student.dto.AuthStudentRequestDto;
import br.com.mickaelsantos.api_cursos.modules.student.usecases.AuthStudentUseCase;

@RestController
@RequestMapping("/student/auth")
public class AuthStudentController 
{
    @Autowired
    private AuthStudentUseCase authStudentUseCase;

    @PostMapping("getToken")

    public ResponseEntity<Object> getToken(@RequestBody AuthStudentRequestDto authStudentDTO)
    {
        try
        {
            var result = authStudentUseCase.execute(authStudentDTO);
            return ResponseEntity.ok().body(result);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
        
    }
}
