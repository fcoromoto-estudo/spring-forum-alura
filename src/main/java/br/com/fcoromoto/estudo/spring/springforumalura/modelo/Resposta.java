package br.com.fcoromoto.estudo.spring.springforumalura.modelo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter @Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Resposta {

	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Boolean solucao = false;

	private String mensagem;

	private LocalDateTime dataCriacao = LocalDateTime.now();

	@ManyToOne
	private Topico topico;

	@ManyToOne
	private Usuario autor;

	public String getNomeAutor() {
		if(Objects.isNull(autor)){
			return null;
		}
		return autor.getNome();
	}
}
