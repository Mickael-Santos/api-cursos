package br.com.mickaelsantos.api_cursos.modules.course.usecases;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mickaelsantos.api_cursos.exceptions.CategoryNotFoundException;
import br.com.mickaelsantos.api_cursos.modules.course.dtos.CategoryResponseDto;
import br.com.mickaelsantos.api_cursos.modules.course.models.Category;
import br.com.mickaelsantos.api_cursos.modules.course.repositories.CategoryRepository;

@Service
public class ListCategoryUseCase
{
    @Autowired
    private CategoryRepository categoryRepository;

    public List<CategoryResponseDto> execute()
    {
        var categories  = categoryRepository.findAllCategories()
        .orElseThrow(() -> {
            throw new CategoryNotFoundException();
        });

        List<CategoryResponseDto> categoriesDTO = new ArrayList<>();
        for(Category category : categories)
        {
            var categoryDTO = CategoryResponseDto.builder()
            .uuId(category.getUuId())
            .name(category.getName())
            .active(category.isActive())
            .build();

            categoriesDTO.add(categoryDTO);
        }

        return categoriesDTO;
    }
}
