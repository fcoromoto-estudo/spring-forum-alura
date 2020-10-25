package br.com.fcoromoto.estudo.spring.springforumalura.dto;

import br.com.fcoromoto.estudo.spring.springforumalura.modelo.Curso;
import br.com.fcoromoto.estudo.spring.springforumalura.modelo.Topico;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.function.Supplier;

@Getter
@Setter
public class TopicoFormDTO {

    @NotNull @NotEmpty @Length(min = 5)
    private String titulo;

    @NotNull @NotEmpty @Length(min = 10)
    private String mensagem;

    @NotNull @NotEmpty
    private String curso;

    public Topico convertToEntity(Supplier<Curso> cursoSuplier) {
        Topico topico = new Topico();
        topico.setMensagem(mensagem);
        topico.setTitulo(titulo);
        topico.setCurso(cursoSuplier.get());
        return topico;
    }
}
