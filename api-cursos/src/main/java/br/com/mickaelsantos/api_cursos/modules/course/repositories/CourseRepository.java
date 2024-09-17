package br.com.mickaelsantos.api_cursos.modules.course.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

import java.util.UUID;

import br.com.mickaelsantos.api_cursos.modules.course.models.Course;



public interface CourseRepository extends JpaRepository<Course, UUID>
{ 
    @Query(value = "SELECT * FROM COURSE C WHERE C.NAME  = :name ", nativeQuery = true)
    Optional<Course> findByName(@Param("name") String name);

    Optional<Course> findByUuId(UUID uuId);

    @Query(value = "SELECT * FROM COURSE C WHERE C.COMPANY = :uuid", nativeQuery = true)
    Optional<List<Course>> findAllCourses(@Param("uuid") UUID companyUuId);
}
