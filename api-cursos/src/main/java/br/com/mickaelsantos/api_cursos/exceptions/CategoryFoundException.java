package br.com.mickaelsantos.api_cursos.exceptions;

public class CategoryFoundException extends RuntimeException
{
    public CategoryFoundException()
    {
        super("Já existe um cadastro pra essa categoria!");
    }
}
