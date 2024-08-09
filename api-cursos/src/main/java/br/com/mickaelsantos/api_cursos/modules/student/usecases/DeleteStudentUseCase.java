package br.com.mickaelsantos.api_cursos.modules.student.usecases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mickaelsantos.api_cursos.exceptions.StudentNotFoundException;
import br.com.mickaelsantos.api_cursos.modules.student.repositories.StudentRepository;

@Service
public class DeleteStudentUseCase 
{
    @Autowired
    private StudentRepository studentRepository;

    public void execute(UUID uuid)
    {
        studentRepository.findByUuId(uuid)
        .orElseThrow(() -> {
            throw new StudentNotFoundException();
        });

        studentRepository.deleteById(uuid);

    }
}
