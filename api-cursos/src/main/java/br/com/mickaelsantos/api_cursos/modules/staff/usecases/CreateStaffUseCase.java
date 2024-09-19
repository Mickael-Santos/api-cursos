package br.com.mickaelsantos.api_cursos.modules.staff.usecases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.mickaelsantos.api_cursos.modules.staff.models.Staff;
import br.com.mickaelsantos.api_cursos.modules.staff.repositories.StaffRepository;

@Service
public class CreateStaffUseCase 
{
    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @EventListener(ContextRefreshedEvent.class)
    public void init()
    {
        Staff staff = new Staff();
        staff.setName("gestao-cursos");
        staff.setUsername("Suporte");
        staff.setPassword(passwordEncoder.encode("spt@1234"));
        staff.setEmail("contato.gestaocursos@gmail.com");

        var staffExists = staffRepository.existsByUsername(staff.getUsername());

        if(staffExists)
        {
            System.out.println("O usuário suporte já existe nessa base, abortando cadastro...");
            return;
        }

        var savedStaff = staffRepository.save(staff);
        System.out.println("Usuário " + savedStaff.getUsername() + " criado...");

    }
}
