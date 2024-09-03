package br.com.mickaelsantos.api_cursos.modules.course.controllers;

import java.util.UUID;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.mickaelsantos.api_cursos.modules.course.dtos.CategoryUpdateRequestDto;
import br.com.mickaelsantos.api_cursos.modules.course.models.Category;
import br.com.mickaelsantos.api_cursos.modules.course.usecases.CreateCategoryUseCase;
import br.com.mickaelsantos.api_cursos.modules.course.usecases.UpdateCategoryUseCase;
import br.com.mickaelsantos.api_cursos.modules.course.usecases.ListCategoryUseCase;
import br.com.mickaelsantos.api_cursos.modules.course.usecases.ToggleCategoryUseCase;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/category")

public class CategoryController 
{
    @Autowired
    private CreateCategoryUseCase createCategoryUseCase;
    @Autowired
    private UpdateCategoryUseCase updateCategoryUseCase;
    @Autowired
    private ListCategoryUseCase listCategoryUseCase;
    @Autowired
    private ToggleCategoryUseCase toggleCategoryUseCase;


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

    @GetMapping("/get")

    public ResponseEntity<Object> get()
    {
        try
        {
            var result = listCategoryUseCase.execute();
            return ResponseEntity.ok().body(result);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PatchMapping("/toggle/{uuid}")

    public ResponseEntity<Object> toggle(@PathVariable UUID uuid, @RequestParam boolean status)
    {
        try
        {
            var result = toggleCategoryUseCase.execute(uuid, status);
            return ResponseEntity.ok().body(result);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
    
}
