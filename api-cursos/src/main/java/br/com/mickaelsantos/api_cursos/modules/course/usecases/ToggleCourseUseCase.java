package br.com.mickaelsantos.api_cursos.modules.course.usecases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mickaelsantos.api_cursos.exceptions.CourseNotFoundException;
import br.com.mickaelsantos.api_cursos.modules.course.models.Course;
import br.com.mickaelsantos.api_cursos.modules.course.repositories.CourseRepository;

import java.util.UUID;

@Service
public class ToggleCourseUseCase
{
    @Autowired
    private CourseRepository courseRepository;

    public Course execute(UUID uuid, boolean status)
    {
        var courseFound = courseRepository.findByUuId(uuid)
        .orElseThrow(()  -> {
            throw new CourseNotFoundException();
        }); 

        courseFound.setActive(status);
        var savedCourse = courseRepository.save(courseFound);

        return savedCourse;

    }
}
