package br.com.mickaelsantos.api_cursos.exceptions;

public class CourseNotFoundException extends RuntimeException
{
    public CourseNotFoundException()
    {
        super("Nenhum curso encontrado!");
    }
}
