package br.com.mickaelsantos.api_cursos.exceptions;

public class StudentNotFoundException extends RuntimeException 
{
    public StudentNotFoundException()
    {
        super("Nenhum estudante encontrado...");
    }
}
