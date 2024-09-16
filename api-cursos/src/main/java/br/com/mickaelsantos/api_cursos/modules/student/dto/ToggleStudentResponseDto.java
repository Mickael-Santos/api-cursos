package br.com.mickaelsantos.api_cursos.modules.student.dto;

import lombok.Data;

@Data
public class ToggleStudentResponseDto 
{
    private String name;

    private boolean status;

    public ToggleStudentResponseDto(String name, boolean status)
    {
        this.name = name;
        this.status = status;
    }
}
