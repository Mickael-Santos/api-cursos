package br.com.mickaelsantos.api_cursos.modules.course.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class CategoryUpdateRequestDto 
{
    private String name;
}
