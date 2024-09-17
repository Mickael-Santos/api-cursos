package br.com.mickaelsantos.api_cursos.modules.course.usecases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mickaelsantos.api_cursos.exceptions.CourseFoundException;
import br.com.mickaelsantos.api_cursos.modules.course.models.Course;
import br.com.mickaelsantos.api_cursos.modules.course.repositories.CourseRepository;

@Service
public class CreateCourseUseCase
{
    @Autowired
    private CourseRepository courseRepository;

    public Course execute(Course course)
    {
        courseRepository.findByName(course.getName())
        .ifPresent((item) -> {
            throw new CourseFoundException();
        });

        var savedCourse = courseRepository.save(course);

        return savedCourse;
    }
}