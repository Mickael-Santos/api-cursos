package br.com.mickaelsantos.api_cursos.modules.company.usecases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.mickaelsantos.api_cursos.exceptions.CompanyFoundException;
import br.com.mickaelsantos.api_cursos.modules.company.models.Company;
import br.com.mickaelsantos.api_cursos.modules.company.repositories.CompanyRepository;

@Service
public class CreateCompanyUseCase 
{
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Company execute(Company company)
    {
        companyRepository.findByUsernameOrEmail
        (
            company.getUsername(),
            company.getEmail()
        ).ifPresent
        (
            (item) -> {
                throw new CompanyFoundException();
            }
        );


        var password = passwordEncoder.encode(company.getPassword());
        company.setPassword(password);
        
        return companyRepository.save(company);
            
    }
}
