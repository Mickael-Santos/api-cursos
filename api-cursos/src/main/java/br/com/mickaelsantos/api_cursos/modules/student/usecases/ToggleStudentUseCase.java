package br.com.mickaelsantos.api_cursos.modules.student.usecases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mickaelsantos.api_cursos.exceptions.StudentNotFoundException;
import br.com.mickaelsantos.api_cursos.modules.student.models.Student;
import br.com.mickaelsantos.api_cursos.modules.student.repositories.StudentRepository;

@Service
public class ToggleStudentUseCase 
{
    @Autowired
    private StudentRepository studentRepository;

    public Student execute(UUID uuid, boolean active) throws StudentNotFoundException
    {
        var student = studentRepository.findByUuId(uuid)
        .orElseThrow(() -> {
            throw new StudentNotFoundException();
        });

        student.setActive(active);
        var SaveResult = studentRepository.save(student);
        return SaveResult;

    }
}
