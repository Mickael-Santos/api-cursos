package br.com.mickaelsantos.api_cursos.modules.course.usecases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mickaelsantos.api_cursos.exceptions.CategoryFoundException;
import br.com.mickaelsantos.api_cursos.modules.course.dtos.CategoryResponseDto;
import br.com.mickaelsantos.api_cursos.modules.course.models.Category;
import br.com.mickaelsantos.api_cursos.modules.course.repositories.CategoryRepository;

@Service
public class CreateCategoryUseCase 
{
    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryResponseDto execute(Category category)
    {
        categoryRepository.findByName(category.getName())
        .ifPresent((item) -> 
        {
            throw new CategoryFoundException();
        });

        var categorySaved = categoryRepository.save(category);

        CategoryResponseDto categoryDTO = CategoryResponseDto.builder()
        .uuId(categorySaved.getUuId())
        .name(categorySaved.getName())
        .build();

        return categoryDTO;
    }
}
