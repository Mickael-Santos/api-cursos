package br.com.mickaelsantos.api_cursos.modules.student.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class StudentResponseDto 
{
    private UUID uuId;

    private String name;

    private String cpf;

    private String email;

    private String password;

    private LocalDateTime created_at;

    private LocalDateTime updated_at;
}
