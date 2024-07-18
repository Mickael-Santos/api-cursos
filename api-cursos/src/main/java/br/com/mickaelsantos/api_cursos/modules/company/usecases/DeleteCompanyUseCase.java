package br.com.mickaelsantos.api_cursos.modules.company.usecases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mickaelsantos.api_cursos.exceptions.CompanyNotFoundException;
import br.com.mickaelsantos.api_cursos.modules.company.dtos.DeleteCompanyResponseDto;
import br.com.mickaelsantos.api_cursos.modules.company.repositories.CompanyRepository;

@Service
public class DeleteCompanyUseCase 
{
    @Autowired
    private CompanyRepository companyRepository;

    public DeleteCompanyResponseDto execute(UUID uuId)
    {
        var companyFound = companyRepository.findById(uuId).
        orElseThrow(() -> {
            throw new CompanyNotFoundException("empresa não encontrada");
        });

        try
        {
            companyRepository.deleteById(uuId);
            var deleteResponseDTO = DeleteCompanyResponseDto.builder()
            .message("empresa deletada com sucesso")
            .isDeleted(true)
            .build();

            return deleteResponseDTO;

        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            var deleteResponseDTO = DeleteCompanyResponseDto.builder()
            .message("Ocorreu um problema ao deletar empresa!")
            .isDeleted(false)
            .build();

            return deleteResponseDTO;
        }

        
    }
}
