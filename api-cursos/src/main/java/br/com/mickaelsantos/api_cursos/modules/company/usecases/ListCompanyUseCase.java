package br.com.mickaelsantos.api_cursos.modules.company.usecases;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mickaelsantos.api_cursos.exceptions.CompanyNotFoundException;
import br.com.mickaelsantos.api_cursos.modules.company.dtos.CompanyResponseDTO;
import br.com.mickaelsantos.api_cursos.modules.company.repositories.CompanyRepository;

@Service
public class ListCompanyUseCase 
{
    @Autowired
    private CompanyRepository companyRepository;
    
    public List<CompanyResponseDTO> execute()
    {
        var companies = companyRepository.findAllCompanies()
        .orElseThrow(() -> {
            throw new CompanyNotFoundException("nenhuma empresa encontrada!");
        });

        List<CompanyResponseDTO> treatedCompanies = new ArrayList<>();

        companies.forEach(x -> {
            CompanyResponseDTO companyResponseDTO = new CompanyResponseDTO();
            companyResponseDTO.setUuId(x.getUuId());
            companyResponseDTO.setName(x.getName());
            companyResponseDTO.setCnpj(x.getCnpj());
            companyResponseDTO.setEmail(x.getEmail());
            companyResponseDTO.setUsername(x.getUsername());
            companyResponseDTO.setPassword(x.getPassword());
            companyResponseDTO.setActive(x.isActive());
            companyResponseDTO.setCreated_at(x.getCreated_at());
            companyResponseDTO.setUpdated_at(x.getUpdated_at());

            treatedCompanies.add(companyResponseDTO);
        });
        return treatedCompanies;
    }
}
