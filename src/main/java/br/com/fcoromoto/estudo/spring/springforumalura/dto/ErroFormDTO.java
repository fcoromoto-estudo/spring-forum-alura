package br.com.fcoromoto.estudo.spring.springforumalura.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ErroFormDTO {

    private final String campo;
    private final String error;

    public static ErroFormDTO of(String campo, String error) {
        return new ErroFormDTO(campo, error);
    }
}
