package br.com.mickaelsantos.api_cursos.modules.company.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class CompanyResponseDTO 
{
    private UUID uuId;

    private String name;

    private String cnpj;

    private String email;
    
    private String username;

    private String password;

    private LocalDateTime created_at;

    private LocalDateTime updated_at;
}
