package br.com.fcoromoto.estudo.spring.springforumalura.controller;

import br.com.fcoromoto.estudo.spring.springforumalura.dto.TopicoAtualizacaoFormDTO;
import br.com.fcoromoto.estudo.spring.springforumalura.dto.TopicoDTO;
import br.com.fcoromoto.estudo.spring.springforumalura.dto.TopicoDetalheDTO;
import br.com.fcoromoto.estudo.spring.springforumalura.dto.TopicoFormDTO;
import br.com.fcoromoto.estudo.spring.springforumalura.service.TopicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

import static org.springframework.data.domain.Sort.Direction.ASC;

@RestController
@RequestMapping("topicos")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;


    @GetMapping
    public Page<TopicoDTO> listar(@RequestParam(required = false) String nomeCurso,
                                  @PageableDefault(sort = "id", direction = ASC) Pageable pageable){

        return topicoService.listar(nomeCurso, pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicoDetalheDTO> consultarPorId(@PathVariable Long id){
        Optional<TopicoDetalheDTO> topicoOptional = topicoService.consultarPorId(id);

        if ( ! topicoOptional.isPresent() ) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(topicoOptional.get());
    }

    @PostMapping
    public ResponseEntity<TopicoDTO> incluir(@RequestBody @Valid TopicoFormDTO form, UriComponentsBuilder uriBuilder){
        TopicoDTO topicoDTO = topicoService.incluir(form);

        URI uri = uriBuilder.path("topicos/{id}").buildAndExpand(topicoDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(topicoDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TopicoDTO> atualizar(@PathVariable Long id, @RequestBody @Valid TopicoAtualizacaoFormDTO form){
        TopicoDTO topicoDTO = topicoService.atualizar(id, form);
        return ResponseEntity.ok(topicoDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> remover(@PathVariable Long id){
        topicoService.remover(id);
        return ResponseEntity.ok().build();
    }

}
