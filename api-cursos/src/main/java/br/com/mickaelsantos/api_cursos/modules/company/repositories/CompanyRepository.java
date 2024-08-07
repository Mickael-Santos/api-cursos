package br.com.mickaelsantos.api_cursos.modules.company.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.mickaelsantos.api_cursos.modules.company.models.Company;
import java.util.List;


public interface CompanyRepository extends JpaRepository<Company, UUID>
{
    Optional<Company> findByUsernameOrEmail(String username, String email);

    Optional<Company> findByUsername(String username);

    Optional<Company> findByUuId(UUID uuId);

    @Query(value = "SELECT * FROM COMPANY", nativeQuery = true)
    Optional<List<Company>> findAllCompanies();

}
