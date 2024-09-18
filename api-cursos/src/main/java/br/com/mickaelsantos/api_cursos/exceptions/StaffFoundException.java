package br.com.mickaelsantos.api_cursos.exceptions;


public class StaffFoundException extends RuntimeException
{
    public StaffFoundException()
    {
        super("Já existe um usuário supervisor(staff) cadastrado!");
    }
}
