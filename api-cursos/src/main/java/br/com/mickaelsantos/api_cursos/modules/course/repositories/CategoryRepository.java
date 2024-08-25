package br.com.mickaelsantos.api_cursos.modules.course.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.mickaelsantos.api_cursos.modules.course.models.Category;
import java.util.Optional;


public interface CategoryRepository extends JpaRepository<Category, UUID>
{
    @Query(value = "SELECT * FROM CATEGORY C WHERE C.NAME = :name ", nativeQuery = true)
    Optional<Category> findByName(@Param("name") String name);
}




