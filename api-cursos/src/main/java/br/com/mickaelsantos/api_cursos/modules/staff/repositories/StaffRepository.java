package br.com.mickaelsantos.api_cursos.modules.staff.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.mickaelsantos.api_cursos.modules.staff.models.Staff;

public interface StaffRepository extends JpaRepository<Staff, UUID>
{
    boolean existsByUsername(String username);
}
