package br.com.mickaelsantos.api_cursos.modules.course.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class CourseResponseDto 
{
    private UUID uuid;

    private String name;

    private UUID category;

    private UUID company;

    private boolean active;

    private LocalDateTime created_at;

    private LocalDateTime updated_at;
}
