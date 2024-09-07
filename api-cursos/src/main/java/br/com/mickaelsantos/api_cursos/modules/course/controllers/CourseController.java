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

import br.com.mickaelsantos.api_cursos.interfaces.IController;
import br.com.mickaelsantos.api_cursos.modules.course.dtos.CourseRequestDto;
import br.com.mickaelsantos.api_cursos.modules.course.models.Course;
import br.com.mickaelsantos.api_cursos.modules.course.usecases.CreateCourseUseCase;
import br.com.mickaelsantos.api_cursos.modules.course.usecases.UpdateCourseUseCase;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/course")

public class CourseController implements IController<Course, CourseRequestDto>
{
    @Autowired
    private CreateCourseUseCase createCourseUseCase;
    @Autowired
    private UpdateCourseUseCase updateCourseUseCase;

    @PostMapping("/create")
    @Override
    public ResponseEntity<Object> create(@Valid @RequestBody Course course) 
    {
        try
        {
            var result = createCourseUseCase.execute(course);
            return ResponseEntity.ok().body(result);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PutMapping("/update/{uuid}")
    @Override
    public ResponseEntity<Object> update(@Valid @PathVariable UUID uuid, @RequestBody CourseRequestDto courseDTO) 
    {
        try
        {
           var result = updateCourseUseCase.execute(uuid, courseDTO);
           return ResponseEntity.ok().body(result);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
         
    }

    @Override
    public ResponseEntity<Object> get() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    }

    @Override
    public ResponseEntity<Object> delete(UUID uuid) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }
    
}
