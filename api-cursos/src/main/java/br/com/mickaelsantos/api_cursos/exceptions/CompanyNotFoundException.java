package br.com.mickaelsantos.api_cursos.exceptions;

public class CompanyNotFoundException extends RuntimeException
{
    public CompanyNotFoundException()
    {
        super("Empresa não encontrada!");
    }
}
