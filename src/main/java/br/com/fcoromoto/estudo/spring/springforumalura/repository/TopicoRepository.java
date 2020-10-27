package br.com.fcoromoto.estudo.spring.springforumalura.repository;

import br.com.fcoromoto.estudo.spring.springforumalura.modelo.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

    Page<Topico> findByCursoNome(String nomeCurso, Pageable pageble);

    @Query(value = "Select t.id  from Topico t where t.id = :id")
    Optional<Long> isPresent(Long id);
}
