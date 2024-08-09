package br.com.mickaelsantos.api_cursos.modules.student.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class DeleteStudentResponseDto 
{
    private String message;

    private boolean isDeleted;
}
