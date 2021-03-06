package br.com.fcoromoto.estudo.spring.springforumalura.dto;

import br.com.fcoromoto.estudo.spring.springforumalura.modelo.StatusTopico;
import br.com.fcoromoto.estudo.spring.springforumalura.modelo.Topico;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

@Getter
public class TopicoDetalheDTO {

    private Long id;
    private String titulo;
    private String mensagem;
    private LocalDateTime dataCriacao;
    private StatusTopico status;
    private String autor;
    private String curso;
    private List<RespostaDetalheDTO> respostas;


    public static TopicoDetalheDTO of(Topico topico){
        TopicoDetalheDTO dto = new TopicoDetalheDTO();
        dto.titulo = topico.getTitulo();
        dto.id = topico.getId();
        dto.mensagem = topico.getMensagem();
        dto.dataCriacao = topico.getDataCriacao();
        dto.status = topico.getStatus();
        dto.autor = topico.getNomeAutor();
        dto.curso = topico.getNomeCurso();

        if( Objects.nonNull( topico.getRespostas() ) && ( ! topico.getRespostas().isEmpty() ) ){
            dto.respostas = topico.getRespostas().stream().map(RespostaDetalheDTO::fromResposta).collect(toList());
        }

        return dto;
    }
}
