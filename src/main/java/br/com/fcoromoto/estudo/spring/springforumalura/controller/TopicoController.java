package br.com.fcoromoto.estudo.spring.springforumalura.controller;

import br.com.fcoromoto.estudo.spring.springforumalura.dto.TopicoDTO;
import br.com.fcoromoto.estudo.spring.springforumalura.modelo.Curso;
import br.com.fcoromoto.estudo.spring.springforumalura.modelo.Topico;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class TopicoController {

    @RequestMapping("topicos")
    public List<TopicoDTO> listar(){
        Topico topico = new Topico("Erro Nulpointer", "Como corrigir NulPointerException",
                new Curso("Java","Programação"));

        return TopicoDTO.fromTopicos(Arrays.asList(topico));
    }

}
