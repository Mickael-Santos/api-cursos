package br.com.mickaelsantos.api_cursos.modules.company.dtos;

import org.hibernate.validator.constraints.Normalized;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class AuthCompanyResponseDto 
{
    private String acess_token;

    private Long espiresIn;
}
