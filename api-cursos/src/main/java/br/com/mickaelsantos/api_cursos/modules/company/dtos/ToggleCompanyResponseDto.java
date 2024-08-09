package br.com.mickaelsantos.api_cursos.modules.company.dtos;

import lombok.Data;

@Data

public class ToggleCompanyResponseDto 
{

    private String name;

    private boolean active;

    public ToggleCompanyResponseDto(String name, boolean active)
    {
        this.name = name;
        this.active = active;
    }


}
