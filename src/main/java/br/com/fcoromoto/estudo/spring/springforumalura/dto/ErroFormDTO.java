package br.com.fcoromoto.estudo.spring.springforumalura.dto;

public class ErroFormDTO {

    private final String campo;
    private final String error;

    public ErroFormDTO(String campo, String error) {
        this.campo = campo;
        this.error = error;
    }

    public String getCampo() {
        return campo;
    }

    public String getError() {
        return error;
    }
}
