package br.com.mickaelsantos.api_cursos.modules.company.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mickaelsantos.api_cursos.modules.company.dtos.AuthCompanyDto;
import br.com.mickaelsantos.api_cursos.modules.company.usecases.AuthCompanyUseCase;

@RestController
@RequestMapping("/company/auth")

public class AuthCompanyController 
{
    @Autowired
    private AuthCompanyUseCase authCompanyUseCase;


    @GetMapping("getToken")

    public ResponseEntity<Object> getToken(@RequestBody AuthCompanyDto authCompanyDto)
    {
        try
        {
            var result = authCompanyUseCase.execute(authCompanyDto);
            return ResponseEntity.ok().body(result);
        }
        catch(Exception ex)
        {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
