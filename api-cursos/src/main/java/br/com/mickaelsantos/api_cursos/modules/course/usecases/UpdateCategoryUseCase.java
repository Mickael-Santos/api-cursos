package br.com.mickaelsantos.api_cursos.modules.course.usecases;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.mickaelsantos.api_cursos.exceptions.CategoryNotFoundException;
import br.com.mickaelsantos.api_cursos.modules.course.dtos.CategoryResponseDto;
import br.com.mickaelsantos.api_cursos.modules.course.dtos.CategoryUpdateRequestDto;
import br.com.mickaelsantos.api_cursos.modules.course.repositories.CategoryRepository;

@Service
public class UpdateCategoryUseCase 
{
    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryResponseDto execute(UUID uuid, CategoryUpdateRequestDto company)
    {
        var companyFound = categoryRepository.findByUuId(uuid)
        .orElseThrow(() -> {
            throw new CategoryNotFoundException();
        });

        
        companyFound.setName(company.getName());

        var categorySaved = categoryRepository.save(companyFound);

        CategoryResponseDto categoryDTO = CategoryResponseDto.builder()
        .uuId(categorySaved.getUuId())
        .name(categorySaved.getName())
        .active(categorySaved.isActive())
        .build();

        return categoryDTO;
    }
}
    