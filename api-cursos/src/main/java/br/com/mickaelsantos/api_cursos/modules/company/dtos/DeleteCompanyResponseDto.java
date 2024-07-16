package br.com.mickaelsantos.api_cursos.modules.company.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeleteCompanyResponseDto 
{
    private String message;

    private boolean isDeleted;
}
