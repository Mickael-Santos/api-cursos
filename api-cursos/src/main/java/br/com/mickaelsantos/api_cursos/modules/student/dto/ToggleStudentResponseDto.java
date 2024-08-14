package br.com.mickaelsantos.api_cursos.modules.student.dto;

import lombok.Data;

@Data
public class ToggleStudentResponseDto 
{
    private String name;

    private boolean active;

    public ToggleStudentResponseDto(String name, boolean active)
    {
        this.name = name;
        this.active = active;
    }
}
