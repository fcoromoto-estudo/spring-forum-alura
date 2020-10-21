package br.com.fcoromoto.estudo.spring.springforumalura.controller;

import br.com.fcoromoto.estudo.spring.springforumalura.dto.ErroFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ResourceErrorAdvice {

    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ErroFormDTO> handle(MethodArgumentNotValidException exception){

        List<FieldError> erros = exception.getBindingResult().getFieldErrors();
        List<ErroFormDTO> errosDTO = new ArrayList<>(erros.size());

        erros.forEach(fieldError -> {
            String mensagem = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
            String campo = fieldError.getField();
            errosDTO.add(new ErroFormDTO(campo, mensagem));
        });

        return errosDTO;

    }
}
