package br.com.mickaelsantos.api_cursos.exceptions;

public class CategoryNotFoundException extends RuntimeException
{
    public CategoryNotFoundException()
    {
        super("Categoria não encontrada!");
    }   
}
