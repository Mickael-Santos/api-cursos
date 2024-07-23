package br.com.mickaelsantos.api_cursos.exceptions;

public class StudentFoundException extends RuntimeException
{
    public StudentFoundException(String message)
    {
        super(message);
    }
}
