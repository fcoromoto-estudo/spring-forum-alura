package br.com.fcoromoto.estudo.spring.springforumalura.controller;

import br.com.fcoromoto.estudo.spring.springforumalura.dto.TopicoAtualizacaoFormDTO;
import br.com.fcoromoto.estudo.spring.springforumalura.dto.TopicoDTO;
import br.com.fcoromoto.estudo.spring.springforumalura.dto.TopicoDetalheDTO;
import br.com.fcoromoto.estudo.spring.springforumalura.dto.TopicoFormDTO;
import br.com.fcoromoto.estudo.spring.springforumalura.modelo.Topico;
import br.com.fcoromoto.estudo.spring.springforumalura.repository.CursoRepository;
import br.com.fcoromoto.estudo.spring.springforumalura.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping
    public List<TopicoDTO> listar(){
        return TopicoDTO.fromTopicos(topicoRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicoDetalheDTO> consultarPorId(@PathVariable Long id){
        Optional<Topico> topicoOptional = topicoRepository.findById(id);

        if ( ! topicoOptional.isPresent() ) {
            return ResponseEntity.notFound().build();
        }

        Topico topico = topicoOptional.get();
        TopicoDetalheDTO topicoDetalheDTO = TopicoDetalheDTO.fromTopico(topico);
        return ResponseEntity.ok(topicoDetalheDTO);
    }

    @Transactional
    @PostMapping
    public ResponseEntity<TopicoDTO> incluir(@RequestBody @Valid TopicoFormDTO form, UriComponentsBuilder uriBuilder){
        Topico topico = form.convertToEntity(cursoRepository);
        topicoRepository.save(topico);

        URI uri = uriBuilder.path("topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(TopicoDTO.fromTopico(topico));
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<TopicoDTO> atualizar(@PathVariable Long id, @RequestBody @Valid TopicoAtualizacaoFormDTO form){
        Optional<Topico> topicoOptional = topicoRepository.findById(id);

        if ( ! topicoOptional.isPresent() ) {
            return ResponseEntity.notFound().build();
        }

        Topico topico = form.atualizar(topicoOptional.get());
        return ResponseEntity.ok(TopicoDTO.fromTopico(topico));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<?> remover(@PathVariable Long id){
        Optional<Topico> topicoOptional = topicoRepository.findById(id);

        if ( ! topicoOptional.isPresent() ) {
            return ResponseEntity.notFound().build();
        }

        topicoRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
