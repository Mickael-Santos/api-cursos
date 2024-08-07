package br.com.mickaelsantos.api_cursos.modules.student.usecases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mickaelsantos.api_cursos.exceptions.StudentNotFoundException;
import br.com.mickaelsantos.api_cursos.modules.student.models.Student;
import br.com.mickaelsantos.api_cursos.modules.student.repositories.StudentRepository;

@Service
public class ListStudentUseCase 
{
    @Autowired
    private StudentRepository studentRepository;

    public List<Student> execute() 
    {
        var students = studentRepository.findAllStudents()
        .orElseThrow(() -> {
            throw new StudentNotFoundException();
        });

        return students;
    }
}
