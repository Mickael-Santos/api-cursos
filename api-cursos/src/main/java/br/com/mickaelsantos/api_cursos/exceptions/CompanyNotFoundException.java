package br.com.mickaelsantos.api_cursos.exceptions;

public class CompanyNotFoundException extends RuntimeException
{
    public CompanyNotFoundException(String message)
    {
        super(message);
    }
}
