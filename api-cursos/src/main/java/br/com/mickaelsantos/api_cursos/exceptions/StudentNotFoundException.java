package br.com.mickaelsantos.api_cursos.exceptions;

public class StudentNotFoundException extends RuntimeException 
{
    public StudentNotFoundException()
    {
        super("O estudante não está cadastrado");
    }
}
