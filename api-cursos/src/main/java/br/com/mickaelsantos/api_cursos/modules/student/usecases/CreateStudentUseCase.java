package br.com.mickaelsantos.api_cursos.modules.student.usecases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.mickaelsantos.api_cursos.exceptions.StudentFoundException;
import br.com.mickaelsantos.api_cursos.modules.student.models.Student;
import br.com.mickaelsantos.api_cursos.modules.student.repositories.StudentRepository;

@Service
public class CreateStudentUseCase 
{
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Student execute(Student student)
    {
        studentRepository.findByUsernameOrEmail(student.getUsername(), student.getEmail())
        .ifPresent((item) -> {
            throw new StudentFoundException("Já existe um aluno cadastrado com esse [username/email]");
        });

        var password = passwordEncoder.encode(student.getPassword());
        student.setActive(true);
        student.setPassword(password);
        
        var createdStudent = studentRepository.save(student);

        return createdStudent;
    }
}
