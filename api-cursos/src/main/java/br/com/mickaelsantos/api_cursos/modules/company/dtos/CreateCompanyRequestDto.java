package br.com.mickaelsantos.api_cursos.modules.company.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class CreateCompanyRequestDto 
{
    private String name;

    private String cnpj;

    private String email;

    private String username;

    private String password;
}
