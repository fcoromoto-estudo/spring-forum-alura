package br.com.fcoromoto.estudo.spring.springforumalura.service;

import br.com.fcoromoto.estudo.spring.springforumalura.dto.TopicoAtualizacaoFormDTO;
import br.com.fcoromoto.estudo.spring.springforumalura.dto.TopicoDTO;
import br.com.fcoromoto.estudo.spring.springforumalura.dto.TopicoDetalheDTO;
import br.com.fcoromoto.estudo.spring.springforumalura.dto.TopicoFormDTO;
import br.com.fcoromoto.estudo.spring.springforumalura.modelo.Topico;
import br.com.fcoromoto.estudo.spring.springforumalura.repository.CursoRepository;
import br.com.fcoromoto.estudo.spring.springforumalura.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Objects;
import java.util.Optional;


@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Cacheable(value = "topicos")
    public Page<TopicoDTO> listar(String nomeCurso, Pageable pageable){

        if(Objects.nonNull(nomeCurso)){
            return TopicoDTO.of(topicoRepository.findByCursoNome(nomeCurso, pageable));
        }

        return TopicoDTO.of(topicoRepository.findAll(pageable));
    }

    public Optional<TopicoDetalheDTO> consultarPorId(@PathVariable Long id){
        Optional<Topico> topicoOptional = topicoRepository.findById(id);

        if ( ! topicoOptional.isPresent() ) {
            return Optional.empty();
        }

        Topico topico = topicoOptional.get();
        TopicoDetalheDTO topicoDetalheDTO = TopicoDetalheDTO.of(topico);
        return Optional.of(topicoDetalheDTO);
    }

    @Transactional
    @CacheEvict(value = "topicos", allEntries = true)
    public TopicoDTO incluir(@Valid TopicoFormDTO form){
        Topico topico = form.convertToEntity(() -> cursoRepository.findByNome(form.getCurso()));
        topicoRepository.save(topico);
        return TopicoDTO.of(topico);
    }

    @Transactional
    @CacheEvict(value = "topicos", allEntries = true)
    public TopicoDTO atualizar(Long id, @Valid TopicoAtualizacaoFormDTO form){
        Topico topico = topicoRepository.findById(id).orElseThrow(NullPointerException::new);
        form.atualizar(topico);
        return TopicoDTO.of(topico);
    }

    @Transactional
    @CacheEvict(value = "topicos", allEntries = true)
    public void remover(@PathVariable Long id){
        topicoRepository.deleteById(id);
    }
}
