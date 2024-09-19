package br.com.mickaelsantos.api_cursos.exceptions;

public class StaffNofFoundException extends RuntimeException
{
    public StaffNofFoundException()
    {
        super("Nenhum staff encontrado na base de dados!");
    }
}
