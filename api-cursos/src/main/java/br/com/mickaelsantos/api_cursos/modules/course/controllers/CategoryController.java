package br.com.mickaelsantos.api_cursos.modules.course.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mickaelsantos.api_cursos.modules.course.models.Category;
import br.com.mickaelsantos.api_cursos.modules.course.usecases.CreateCategoryUseCase;

@RestController
@RequestMapping("/category")

public class CategoryController 
{
    @Autowired
    private CreateCategoryUseCase createCategoryUseCase;

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
}
