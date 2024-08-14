package br.com.mickaelsantos.api_cursos.modules.student.dto;


import lombok.Builder;
import lombok.Data;


@Data
@Builder

public class AuthStudentResponseDto 
{
    private String token;

    private Long expiresIn;
}
