package br.com.mickaelsantos.api_cursos.modules.company.usecases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.mickaelsantos.api_cursos.exceptions.CompanyNotFoundException;
import br.com.mickaelsantos.api_cursos.modules.company.dtos.CompanyUpdateRequestDto;
import br.com.mickaelsantos.api_cursos.modules.company.models.Company;
import br.com.mickaelsantos.api_cursos.modules.company.repositories.CompanyRepository;

@Service
public class UpdateCompanyUseCase 
{
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Company execute(UUID uuid, CompanyUpdateRequestDto company)
    {
        var companyFound = companyRepository.findByUuId(uuid)
        .orElseThrow(() -> {
            throw new CompanyNotFoundException("empresa não encontrada!");
        });

        companyFound.setName(company.getName());
        companyFound.setCnpj(company.getCnpj());
        companyFound.setEmail(company.getEmail());
        companyFound.setUsername(company.getUsername());
        companyFound.setPassword(passwordEncoder.encode(company.getPassword()));

        var result = companyRepository.save(companyFound);

        return result;
    }
}
