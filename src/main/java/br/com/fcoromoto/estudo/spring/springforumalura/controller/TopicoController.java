package br.com.fcoromoto.estudo.spring.springforumalura.controller;

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
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Objects;
import java.util.Optional;

import static org.springframework.data.domain.Sort.Direction.ASC;

@RestController
@RequestMapping("topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping
    @Cacheable(value = "topicos")
    public Page<TopicoDTO> listar(@RequestParam(required = false) String nomeCurso,
                                  @PageableDefault(sort = "id", direction = ASC) Pageable pageable){


        if(Objects.nonNull(nomeCurso)){
            return TopicoDTO.of(topicoRepository.findByCursoNome(nomeCurso, pageable));
        }

        return TopicoDTO.of(topicoRepository.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicoDetalheDTO> consultarPorId(@PathVariable Long id){
        Optional<Topico> topicoOptional = topicoRepository.findById(id);

        if ( ! topicoOptional.isPresent() ) {
            return ResponseEntity.notFound().build();
        }

        Topico topico = topicoOptional.get();
        TopicoDetalheDTO topicoDetalheDTO = TopicoDetalheDTO.of(topico);
        return ResponseEntity.ok(topicoDetalheDTO);
    }

    @PostMapping
    @Transactional
    @CacheEvict(value = "topicos", allEntries = true)
    public ResponseEntity<TopicoDTO> incluir(@RequestBody @Valid TopicoFormDTO form, UriComponentsBuilder uriBuilder){
        Topico topico = form.convertToEntity(() -> cursoRepository.findByNome(form.getCurso()));

        topicoRepository.save(topico);

        URI uri = uriBuilder.path("topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(TopicoDTO.of(topico));
    }

    @PutMapping("/{id}")
    @Transactional
    @CacheEvict(value = "topicos", allEntries = true)
    public ResponseEntity<TopicoDTO> atualizar(@PathVariable Long id, @RequestBody @Valid TopicoAtualizacaoFormDTO form){
        Optional<Topico> topicoOptional = topicoRepository.findById(id);

        if ( ! topicoOptional.isPresent() ) {
            return ResponseEntity.notFound().build();
        }

        Topico topico = form.atualizar(topicoOptional.get());
        return ResponseEntity.ok(TopicoDTO.of(topico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    @CacheEvict(value = "topicos", allEntries = true)
    public ResponseEntity<?> remover(@PathVariable Long id){
        Optional<Topico> topicoOptional = topicoRepository.findById(id);

        if ( ! topicoOptional.isPresent() ) {
            return ResponseEntity.notFound().build();
        }

        topicoRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
