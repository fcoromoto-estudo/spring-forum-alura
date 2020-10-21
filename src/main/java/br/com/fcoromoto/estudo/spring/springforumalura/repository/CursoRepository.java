package br.com.fcoromoto.estudo.spring.springforumalura.repository;

import br.com.fcoromoto.estudo.spring.springforumalura.modelo.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {

    Curso findByNome(String curso);
}
