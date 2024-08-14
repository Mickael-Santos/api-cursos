package br.com.mickaelsantos.api_cursos.modules.student.dto;

import lombok.Data;

@Data

public class AuthStudentRequestDto 
{
    private String username;

    private String password;
}
