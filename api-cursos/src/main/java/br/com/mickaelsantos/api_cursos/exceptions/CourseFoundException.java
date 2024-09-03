package br.com.mickaelsantos.api_cursos.exceptions;

public class CourseFoundException extends RuntimeException 
{
    public CourseFoundException()
    {
        super("Já existe um curso com este nome cadastrado!");
    }
}
