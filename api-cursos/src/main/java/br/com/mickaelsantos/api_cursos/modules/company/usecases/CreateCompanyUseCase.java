package br.com.mickaelsantos.api_cursos.modules.company.usecases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.mickaelsantos.api_cursos.exceptions.CompanyFoundException;
import br.com.mickaelsantos.api_cursos.modules.company.dtos.CreateCompanyRequestDto;
import br.com.mickaelsantos.api_cursos.modules.company.models.Company;
import br.com.mickaelsantos.api_cursos.modules.company.repositories.CompanyRepository;

@Service
public class CreateCompanyUseCase 
{
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Company execute(CreateCompanyRequestDto companyDTO)
    {
        companyRepository.findByUsernameOrEmail
        (
            companyDTO.getUsername(),
            companyDTO.getEmail()
        ).ifPresent
        (
            (item) -> {
                throw new CompanyFoundException();
            }
        );

        var company = new Company();
        company.setName(companyDTO.getName());
        company.setCnpj(companyDTO.getCnpj());
        company.setEmail(companyDTO.getEmail());
        company.setUsername(companyDTO.getUsername());
        company.setPassword(companyDTO.getPassword());

        var password = passwordEncoder.encode(company.getPassword());
        company.setPassword(password);
        
        return companyRepository.save(company);
            
    }
}
