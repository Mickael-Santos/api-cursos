package br.com.mickaelsantos.api_cursos.modules.course.usecases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mickaelsantos.api_cursos.exceptions.CourseNotFoundException;
import br.com.mickaelsantos.api_cursos.modules.course.dtos.CourseRequestDto;
import br.com.mickaelsantos.api_cursos.modules.course.dtos.CourseResponseDto;
import br.com.mickaelsantos.api_cursos.modules.course.models.Course;
import br.com.mickaelsantos.api_cursos.modules.course.repositories.CourseRepository;

@Service
public class UpdateCourseUseCase 
{
    @Autowired
    private CourseRepository courseRepository;

    public CourseResponseDto execute(UUID uuid, CourseRequestDto course)
    {
        var courseFound = courseRepository.findByUuId(uuid)
        .orElseThrow(() ->{
            throw new CourseNotFoundException();
        });

        courseFound.setName(course.getName());
        courseFound.setCategory(course.getCategory());
        courseFound.setCompany(course.getCompany());

        var savedCourse = courseRepository.save(courseFound);

        CourseResponseDto courseDTO = CourseResponseDto.builder()
        .name(savedCourse.getName())
        .category(savedCourse.getCategory())
        .company(savedCourse.getCompany())
        .active(savedCourse.isActive())
        .created_at(savedCourse.getCreated_at())
        .updated_at(savedCourse.getUpdated_at())
        .build();
        
        return courseDTO;
    }
}
