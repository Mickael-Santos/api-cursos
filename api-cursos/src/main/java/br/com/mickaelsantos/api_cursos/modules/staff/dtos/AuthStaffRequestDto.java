package br.com.mickaelsantos.api_cursos.modules.staff.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class AuthStaffRequestDto 
{
    private String username;

    private String password;
}
