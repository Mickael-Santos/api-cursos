package br.com.mickaelsantos.api_cursos.modules.company.dtos;

import java.util.UUID;
import lombok.Data;

@Data

public class CompanyUpdateRequestDto 
{
    private UUID uuId;

    private String name;

    private String cnpj;

    private String email;
    
    private String username;

    private String password;
}
