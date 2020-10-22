package br.com.fcoromoto.estudo.spring.springforumalura.dto;

import br.com.fcoromoto.estudo.spring.springforumalura.modelo.Resposta;

public class RespostaDetalheDTO {

    private final String mensagem;
    private final String autor;

    private RespostaDetalheDTO(String mensagem, String autor) {
        this.mensagem = mensagem;
        this.autor = autor;
    }

    public static RespostaDetalheDTO fromResposta(Resposta resposta){
        return new RespostaDetalheDTO(resposta.getMensagem(), resposta.getNomeAutor());
    }

    public String getMensagem() {
        return mensagem;
    }

    public String getAutor() {
        return autor;
    }
}
