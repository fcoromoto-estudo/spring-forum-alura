package br.com.fcoromoto.estudo.spring.springforumalura.dto;

import br.com.fcoromoto.estudo.spring.springforumalura.modelo.Resposta;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RespostaDetalheDTO {

    private final String mensagem;
    private final String autor;

    public static RespostaDetalheDTO fromResposta(Resposta resposta){
        return new RespostaDetalheDTO(resposta.getMensagem(), resposta.getNomeAutor());
    }
}
