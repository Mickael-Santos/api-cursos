package br.com.mickaelsantos.api_cursos.modules.course.usecases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mickaelsantos.api_cursos.exceptions.CourseNotFoundException;
import br.com.mickaelsantos.api_cursos.modules.course.repositories.CourseRepository;

@Service
public class DeleteCourseUseCase 
{
    @Autowired
    private CourseRepository courseRepository;

    public void execute(UUID uuid)
    {
        courseRepository.findById(uuid)
        .orElseThrow(() -> {
            throw new CourseNotFoundException();
        });

        courseRepository.deleteById(uuid);

    }
}
