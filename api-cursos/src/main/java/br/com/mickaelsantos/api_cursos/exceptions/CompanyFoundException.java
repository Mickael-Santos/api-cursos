package br.com.mickaelsantos.api_cursos.exceptions;

public class CompanyFoundException extends RuntimeException
{
    public CompanyFoundException()
    {
        super("Já existe uma empresa com esse cadastro");
    }
}
