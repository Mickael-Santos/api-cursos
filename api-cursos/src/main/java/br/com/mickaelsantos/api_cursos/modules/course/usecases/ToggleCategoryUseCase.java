package br.com.mickaelsantos.api_cursos.modules.course.usecases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mickaelsantos.api_cursos.exceptions.CategoryNotFoundException;
import br.com.mickaelsantos.api_cursos.modules.course.dtos.CategoryResponseDto;
import br.com.mickaelsantos.api_cursos.modules.course.repositories.CategoryRepository;

@Service
public class ToggleCategoryUseCase
{
    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryResponseDto execute(UUID uuid, boolean status)
    {
        var category = categoryRepository.findByUuId(uuid)
        .orElseThrow(() -> {
            throw new CategoryNotFoundException();
        });

        category.setActive(status);

        var categorySaved = categoryRepository.save(category);

        var categoryDTO = CategoryResponseDto.builder()
        .uuId(categorySaved.getUuId())
        .name(categorySaved.getName())
        .active(categorySaved.isActive())
        .build();

        return categoryDTO;
    }
}