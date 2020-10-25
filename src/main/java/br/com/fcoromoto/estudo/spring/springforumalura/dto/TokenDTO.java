package br.com.fcoromoto.estudo.spring.springforumalura.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TokenDTO {

    private final String token;
    private final String tipo;

    public static TokenDTO of(String token, String tipo){
        return new TokenDTO(token, tipo);
    }
}
