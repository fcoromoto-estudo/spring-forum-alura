package br.com.fcoromoto.estudo.spring.springforumalura.dto;

import br.com.fcoromoto.estudo.spring.springforumalura.modelo.Topico;
import br.com.fcoromoto.estudo.spring.springforumalura.repository.CursoRepository;

public class TopicoFormDTO {

    private String titulo;
    private String mensagem;
    private String curso;


    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public Topico convertToEntity(CursoRepository cursoRepository) {
        Topico topico = new Topico();
        topico.setMensagem(mensagem);
        topico.setTitulo(titulo);
        topico.setCurso(cursoRepository.findByNome(curso));
        return topico;
    }
}
