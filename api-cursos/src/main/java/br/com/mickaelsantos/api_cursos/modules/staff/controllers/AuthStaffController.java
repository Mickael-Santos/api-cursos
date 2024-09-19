package br.com.mickaelsantos.api_cursos.modules.staff.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mickaelsantos.api_cursos.modules.staff.dtos.AuthStaffRequestDto;
import br.com.mickaelsantos.api_cursos.modules.staff.usecases.AuthStaffUseCase;

@RestController
@RequestMapping("/staff")

public class AuthStaffController 
{
    @Autowired
    private AuthStaffUseCase authStaffUseCase;

    @GetMapping("/getToken")

    public ResponseEntity<Object> getToken(@RequestBody AuthStaffRequestDto authDto)
    {
        try
        {
            var result = authStaffUseCase.execute(authDto);
            return ResponseEntity.ok().body(result);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
