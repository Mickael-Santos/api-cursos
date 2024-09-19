package br.com.mickaelsantos.api_cursos.modules.staff.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.mickaelsantos.api_cursos.modules.staff.models.Staff;

public interface StaffRepository extends JpaRepository<Staff, UUID>
{
    Optional<Staff> findByUsername(String username);
    boolean existsByUsername(String username);
}
