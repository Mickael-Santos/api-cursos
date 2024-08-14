package br.com.mickaelsantos.api_cursos.modules.company.usecases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mickaelsantos.api_cursos.exceptions.CompanyNotFoundException;
import br.com.mickaelsantos.api_cursos.modules.company.models.Company;
import br.com.mickaelsantos.api_cursos.modules.company.repositories.CompanyRepository;

@Service
public class ToggleCompanyUseCase 
{
    @Autowired
    private CompanyRepository companyRepository;

    public Company execute(UUID uuid, boolean active) 
    {
        var company = companyRepository.findByUuId(uuid)
        .orElseThrow(() -> {
            throw new CompanyNotFoundException("Nenhuma empresa encontrada!");
        });

        company.setActive(active);

        var saveResult = companyRepository.save(company);
        
        return saveResult;
    }
}
