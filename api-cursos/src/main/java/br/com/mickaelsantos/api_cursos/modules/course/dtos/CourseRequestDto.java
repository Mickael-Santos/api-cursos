package br.com.mickaelsantos.api_cursos.modules.course.dtos;



import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class CourseRequestDto 
{

    private String name;

    private UUID category;

    private UUID company;

} 

