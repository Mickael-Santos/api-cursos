package br.com.mickaelsantos.api_cursos.modules.company.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

import br.com.mickaelsantos.api_cursos.modules.company.dtos.CompanyResponseDTO;
import br.com.mickaelsantos.api_cursos.modules.company.dtos.CompanyUpdateRequestDto;
import br.com.mickaelsantos.api_cursos.modules.company.dtos.CreateCompanyRequestDto;
import br.com.mickaelsantos.api_cursos.modules.company.dtos.ToggleCompanyResponseDto;
import br.com.mickaelsantos.api_cursos.modules.company.models.Company;
import br.com.mickaelsantos.api_cursos.modules.company.usecases.CreateCompanyUseCase;
import br.com.mickaelsantos.api_cursos.modules.company.usecases.DeleteCompanyUseCase;
import br.com.mickaelsantos.api_cursos.modules.company.usecases.ListCompanyUseCase;
import br.com.mickaelsantos.api_cursos.modules.company.usecases.ToggleCompanyUseCase;
import br.com.mickaelsantos.api_cursos.modules.company.usecases.UpdateCompanyUseCase;
import jakarta.validation.Valid;

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
    @Autowired
    private ListCompanyUseCase listCompanyUseCase;
    @Autowired
    private ToggleCompanyUseCase toggleCompanyUseCase;

    @PostMapping("/create")

    public ResponseEntity<Object> create(@Valid @RequestBody Company company)
    {
        try
        {

            var requestCompanyDTO = CreateCompanyRequestDto.builder()
            .name(company.getName())
            .cnpj(company.getCnpj())
            .email(company.getEmail())
            .username(company.getUsername())
            .password(company.getPassword())
            .build();

            var result = createCompanyUseCase.execute(requestCompanyDTO);
        
            var responseCompanyDTO = CompanyResponseDTO.builder()
            .uuId(result.getUuId())
            .name(result.getName())
            .cnpj(result.getCnpj())
            .email(result.getEmail())
            .username(result.getUsername())
            .password(result.getPassword())
            .active(result.isActive())
            .created_at(result.getCreated_at())
            .updated_at(result.getUpdated_at())
            .build();
        
            return ResponseEntity.ok().body(responseCompanyDTO);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body(ex.getMessage());
        } 

    }

    @PutMapping("/update/{uuid}")

    public ResponseEntity<Object> update(@Valid @PathVariable UUID uuid, @RequestBody CompanyUpdateRequestDto company)
    {   
        try
        {
            var result = updateCompanyUseCase.execute(uuid, company);

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
        catch(Exception ex)
        {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
        
    }

    @GetMapping("/get")

    public ResponseEntity<Object> get()
    {
        try
        {
            var companies = listCompanyUseCase.execute();
            return ResponseEntity.ok().body(companies);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
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

    @PatchMapping("/active/{uuid}")

    public ResponseEntity<Object> active(@PathVariable UUID uuid, @RequestParam boolean active)
    {
        try
        {
            var result = toggleCompanyUseCase.execute(uuid, active);
            var resultDTO = new ToggleCompanyResponseDto(result.getName(), result.isActive());
    
            return ResponseEntity.ok().body(resultDTO);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
       
    }

}




