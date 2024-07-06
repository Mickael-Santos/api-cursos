package br.com.mickaelsantos.api_cursos.exceptions.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorMessageDto 
{
    private String message;

    private String field;
}
