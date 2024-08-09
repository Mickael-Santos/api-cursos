package br.com.mickaelsantos.api_cursos.modules.student.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.mickaelsantos.api_cursos.modules.student.models.Student;


public interface StudentRepository extends JpaRepository<Student, UUID>
{
     Optional<Student> findByUsernameOrEmail(String username, String email);

     Optional<Student> findByUuId(UUID uuId);

     @Query(value = "SELECT * FROM STUDENT", nativeQuery = true)
     Optional<List<Student>> findAllStudents();

     void deleteById(UUID uuid);
}
