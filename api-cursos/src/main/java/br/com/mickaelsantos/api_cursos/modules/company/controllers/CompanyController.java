package br.com.mickaelsantos.api_cursos.modules.company.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mickaelsantos.api_cursos.modules.company.dtos.CompanyResponseDTO;
import br.com.mickaelsantos.api_cursos.modules.company.models.Company;
import br.com.mickaelsantos.api_cursos.modules.company.usecases.CreateCompanyUseCase;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/company")

public class CompanyController 
{
    @Autowired
    private CreateCompanyUseCase createCompanyUseCase;

    @PostMapping("/create")

    public ResponseEntity<Object> create(@Valid @RequestBody Company company)
    {
 
        var result = createCompanyUseCase.execute(company);
        
        var companyDTO = CompanyResponseDTO.builder()
        .uuId(result.getUuId())
        .name(result.getName())
        .cnpj(result.getCnpj())
        .email(result.getEmail())
        .username(result.getUsername())
        .password(result.getPassword())
        .created_at(result.getCreated_at())
        .updated_at(result.getUpdated_at())
        .build();
        
        return ResponseEntity.ok().body(companyDTO);
        
        


    }
}
