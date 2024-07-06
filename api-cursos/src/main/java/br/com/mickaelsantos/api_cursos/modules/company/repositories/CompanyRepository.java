package br.com.mickaelsantos.api_cursos.modules.company.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.mickaelsantos.api_cursos.modules.company.models.Company;

public interface CompanyRepository extends JpaRepository<Company, UUID>
{
    Optional<Company> findByUsernameOrEmail(String username, String email);

    Optional<Company> findByUsername(String username);
}
