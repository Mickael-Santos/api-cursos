package br.com.mickaelsantos.api_cursos.exceptions;

public class UsernameNotFoundException extends RuntimeException
{
    public UsernameNotFoundException(String message)
    {
        super(message);
    }
}
