package br.com.mickaelsantos.api_cursos.modules.course.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mickaelsantos.api_cursos.modules.course.dtos.CategoryUpdateRequestDto;
import br.com.mickaelsantos.api_cursos.modules.course.models.Category;
import br.com.mickaelsantos.api_cursos.modules.course.usecases.CreateCategoryUseCase;
import br.com.mickaelsantos.api_cursos.modules.course.usecases.UpdateCategoryUseCase;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/category")

public class CategoryController 
{
    @Autowired
    private CreateCategoryUseCase createCategoryUseCase;
    @Autowired
    private UpdateCategoryUseCase updateCategoryUseCase;


    @PostMapping("/create")
    public ResponseEntity<Object> create(@RequestBody Category category)
    {
        try
        {
            var result = createCategoryUseCase.execute(category);

            return ResponseEntity.ok().body(result);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body(ex.getMessage());
        }

    }

    @PutMapping("/update/{uuid}")


    public ResponseEntity<Object> update(@Valid @PathVariable UUID uuid, @RequestBody CategoryUpdateRequestDto category)
    {
        try
        {
            var companyUpdated = updateCategoryUseCase.execute(uuid, category);
            return ResponseEntity.ok().body(companyUpdated);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
