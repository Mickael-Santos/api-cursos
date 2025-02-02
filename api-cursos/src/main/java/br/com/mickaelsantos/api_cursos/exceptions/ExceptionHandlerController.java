package br.com.mickaelsantos.api_cursos.exceptions;


import java.util.ArrayList;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.mickaelsantos.api_cursos.exceptions.dtos.ErrorMessageDto;;

@ControllerAdvice
public class ExceptionHandlerController 
{
    private MessageSource messageSource;

    public ExceptionHandlerController(MessageSource message)
    {
        this.messageSource = message;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorMessageDto>> HandleMethodArgumentNotValidException(MethodArgumentNotValidException ex)
    {
        List<ErrorMessageDto> Dtos = new ArrayList<>();

        ex.getBindingResult().getFieldErrors().forEach(x ->
        {
            String message = messageSource.getMessage(x, LocaleContextHolder.getLocale());

            ErrorMessageDto Error = new ErrorMessageDto(message, x.getField());

            Dtos.add(Error);
        });

        return new ResponseEntity<>(Dtos, HttpStatus.BAD_REQUEST);

    }
}
