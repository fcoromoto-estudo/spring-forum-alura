package br.com.fcoromoto.estudo.spring.springforumalura.dto;

import br.com.fcoromoto.estudo.spring.springforumalura.modelo.Topico;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class TopicoDTO {

    private Long id;
    private String titulo;
    private String mensagem;
    private LocalDateTime dataCriacao;


    public static TopicoDTO fromTopico(Topico topico){
        TopicoDTO dto = new TopicoDTO();
        dto.titulo = topico.getTitulo();
        dto.id = topico.getId();
        dto.mensagem = topico.getMensagem();
        dto.dataCriacao = topico.getDataCriacao();
        return dto;
    }

    @Deprecated
    public static List<TopicoDTO> fromTopicos(List<Topico> topicos){
        return topicos.stream().map(TopicoDTO::fromTopico).collect(Collectors.toList());
    }

    public static Page<TopicoDTO> fromTopicos(Page<Topico> topicos){
        return topicos.map(TopicoDTO::fromTopico);
    }

    public String getTitulo() {
        return titulo;
    }

    public Long getId() {
        return id;
    }

    public String getMensagem() {
        return mensagem;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }
}
