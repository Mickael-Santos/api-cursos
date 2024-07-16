package br.com.mickaelsantos.api_cursos.modules.company.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.mickaelsantos.api_cursos.modules.company.dtos.CompanyResponseDTO;
import br.com.mickaelsantos.api_cursos.modules.company.dtos.CompanyUpdateRequestDto;
import br.com.mickaelsantos.api_cursos.modules.company.models.Company;
import br.com.mickaelsantos.api_cursos.modules.company.usecases.CreateCompanyUseCase;
import br.com.mickaelsantos.api_cursos.modules.company.usecases.DeleteCompanyUseCase;
import br.com.mickaelsantos.api_cursos.modules.company.usecases.UpdateCompanyUseCase;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/company")

public class CompanyController 
{
    @Autowired
    private CreateCompanyUseCase createCompanyUseCase;
    @Autowired
    private UpdateCompanyUseCase updateCompanyUseCase;
    @Autowired
    private DeleteCompanyUseCase deleteCompanyUseCase;

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

    @PutMapping("/update")

    public ResponseEntity<Object> update(@Valid @RequestBody CompanyUpdateRequestDto company)
    {   
        var result = updateCompanyUseCase.execute(company);

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

    @DeleteMapping("/delete")

    public ResponseEntity<Object> delete(@RequestParam("uuId") UUID uuId)
    {
        var result = deleteCompanyUseCase.execute(uuId);

        if(!result.isDeleted())
        {
            return ResponseEntity.badRequest().body(result);
        }

        return ResponseEntity.ok().body(result);
    }

}
