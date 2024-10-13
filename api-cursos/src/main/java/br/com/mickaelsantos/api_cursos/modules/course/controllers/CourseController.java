package br.com.mickaelsantos.api_cursos.modules.course.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.mickaelsantos.api_cursos.interfaces.IController;
import br.com.mickaelsantos.api_cursos.modules.course.dtos.CourseRequestDto;
import br.com.mickaelsantos.api_cursos.modules.course.dtos.CourseResponseDto;
import br.com.mickaelsantos.api_cursos.modules.course.models.Course;
import br.com.mickaelsantos.api_cursos.modules.course.usecases.CreateCourseUseCase;
import br.com.mickaelsantos.api_cursos.modules.course.usecases.DeleteCourseUseCase;
import br.com.mickaelsantos.api_cursos.modules.course.usecases.ListCourseUseCase;
import br.com.mickaelsantos.api_cursos.modules.course.usecases.ToggleCourseUseCase;
import br.com.mickaelsantos.api_cursos.modules.course.usecases.UpdateCourseUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("company/course")

public class CourseController implements IController<Course, CourseRequestDto, HttpServletRequest>
{
    @Autowired
    private CreateCourseUseCase createCourseUseCase;
    @Autowired
    private UpdateCourseUseCase updateCourseUseCase;
    @Autowired
    private ListCourseUseCase listCourseUseCase;
    @Autowired
    private DeleteCourseUseCase deleteCourseUseCase;
    @Autowired
    private ToggleCourseUseCase toggleCourseUseCase;

    @PostMapping("/create")
    @PreAuthorize("hasRole('COMPANY')")
    @Override
    public ResponseEntity<Object> create(@Valid @RequestBody Course course, HttpServletRequest request) 
    {
        try
        {
            var companyUuId = UUID.fromString(request.getAttribute("company_uuid").toString());
            course.setCompany(companyUuId);
            course.setActive(true);
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
    @PreAuthorize("hasRole('COMPANY')")
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

    @GetMapping("/get")
    @PreAuthorize("hasRole('COMPANY')")
   
    public ResponseEntity<Object> get(@RequestParam String name,String category, HttpServletRequest request) 
    {
        try
        {
            var companyUuId = UUID.fromString(request.getAttribute("company_uuid").toString());
            var results = listCourseUseCase.execute(companyUuId, name, category);
            List<CourseResponseDto> coursesDTO = new ArrayList<>();

            results.forEach(x -> 
            {
                var courseDTO = CourseResponseDto.builder()
                .uuid(x.getUuId())
                .active(x.isActive())
                .category(x.getCategory())
                .name(x.getName())
                .company(x.getCompany())
                .created_at(x.getCreated_at())
                .updated_at(x.getUpdated_at())
                .build();

                coursesDTO.add(courseDTO);
            });

            return ResponseEntity.ok().body(coursesDTO);

        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body(ex.getMessage());
        }

    }

    @DeleteMapping("/delete/{uuid}")
    @PreAuthorize("hasRole('COMPANY')")
    @Override
    public ResponseEntity<Object> delete(@PathVariable UUID uuid) 
    {
        try
        {   
            deleteCourseUseCase.execute(uuid);
            return ResponseEntity.ok().body("Curso deletado com sucesso");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PatchMapping("/toggle/{uuid}")
    @PreAuthorize("hasRole('COMPANY')")

    public ResponseEntity<Object> toggle(@PathVariable UUID uuid, @RequestParam boolean status)
    {
        try
        {   
            var result = toggleCourseUseCase.execute(uuid, status);

            CourseResponseDto courseDTO  = CourseResponseDto.builder()
            .uuid(result.getUuId())
            .category(result.getCategory())
            .company(result.getCompany())
            .created_at(result.getCreated_at())
            .updated_at(result.getUpdated_at())
            .active(result.isActive())
            .build();

            return ResponseEntity.ok().body(courseDTO);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body(ex.getMessage());
        }

    } 
}
