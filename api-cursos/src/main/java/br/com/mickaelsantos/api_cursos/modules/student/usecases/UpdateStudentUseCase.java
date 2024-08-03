package br.com.mickaelsantos.api_cursos.modules.student.usecases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.mickaelsantos.api_cursos.exceptions.StudentNotFoundException;
import br.com.mickaelsantos.api_cursos.modules.student.models.Student;
import br.com.mickaelsantos.api_cursos.modules.student.repositories.StudentRepository;

@Service
public class UpdateStudentUseCase 
{
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Student execute(Student student)
    {
        var studentFound = studentRepository.findByUuId(student.getUuId())
        .orElseThrow(()->{
            throw new StudentNotFoundException();
        });

        studentFound.setName(student.getName());
        studentFound.setCpf(student.getCpf());
        studentFound.setEmail(student.getEmail());
        studentFound.setUsername(student.getUsername());
        studentFound.setPassword(passwordEncoder.encode(student.getPassword()));

        var result = studentRepository.save(studentFound);
        return result;
    }
}
