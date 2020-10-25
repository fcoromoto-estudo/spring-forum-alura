package br.com.fcoromoto.estudo.spring.springforumalura.dto;

import br.com.fcoromoto.estudo.spring.springforumalura.modelo.Topico;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

@Getter
public class TopicoDTO {

    private Long id;
    private String titulo;
    private String mensagem;
    private LocalDateTime dataCriacao;


    public static TopicoDTO of(Topico topico){
        TopicoDTO dto = new TopicoDTO();
        dto.titulo = topico.getTitulo();
        dto.id = topico.getId();
        dto.mensagem = topico.getMensagem();
        dto.dataCriacao = topico.getDataCriacao();
        return dto;
    }

    public static Page<TopicoDTO> of(Page<Topico> topicos){
        return topicos.map(TopicoDTO::of);
    }

}
