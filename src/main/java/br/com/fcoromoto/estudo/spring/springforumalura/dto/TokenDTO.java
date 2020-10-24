package br.com.fcoromoto.estudo.spring.springforumalura.dto;

public class TokenDTO {

    private final String token;
    private final String tipo;

    private TokenDTO(String token, String tipo) {
        this.token = token;
        this.tipo = tipo;
    }


    public static TokenDTO of(String token, String tipo){
        return new TokenDTO(token, tipo);
    }

    public String getToken() {
        return token;
    }

    public String getTipo() {
        return tipo;
    }
}
