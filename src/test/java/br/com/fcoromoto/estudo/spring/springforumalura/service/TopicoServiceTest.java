package br.com.fcoromoto.estudo.spring.springforumalura.service;

import br.com.fcoromoto.estudo.spring.springforumalura.dto.TopicoDTO;
import br.com.fcoromoto.estudo.spring.springforumalura.dto.TopicoFormDTO;
import br.com.fcoromoto.estudo.spring.springforumalura.repository.CursoRepository;
import br.com.fcoromoto.estudo.spring.springforumalura.repository.TopicoRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
public class TopicoServiceTest {

    @TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {

        @Bean
        public TopicoService topicoService() {
            return new TopicoService();
        }
    }

    @Autowired
    private TopicoService topicoService;

    @MockBean
    private TopicoRepository topicoRepository;

    @MockBean
    private CursoRepository cursoRepository;

    @Test
    public void deveLancarExceptionQuandoIncluirTopoComTituloNullo(){
//         Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        TopicoFormDTO dto = new TopicoFormDTO();
        TopicoDTO topicoDTO = topicoService.incluir(dto);
    }

}