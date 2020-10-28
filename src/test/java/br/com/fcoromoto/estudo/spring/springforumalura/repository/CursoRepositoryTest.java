package br.com.fcoromoto.estudo.spring.springforumalura.repository;

import br.com.fcoromoto.estudo.spring.springforumalura.modelo.Curso;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class CursoRepositoryTest {

    @Autowired
    private CursoRepository repository;

    @Autowired
    private EntityManager entityManager;

    @Test
    public void deveRetonarCursoQuandoConsultaPorNome() {

        Curso cursoInserido = new Curso();
        cursoInserido.setNome("HTML 5");
        entityManager.persist(cursoInserido);

        String nomeCurso = "HTML 5";
        Curso curso = repository.findByNome(nomeCurso);

        Assert.assertNotNull(curso);
        Assert.assertEquals(nomeCurso, curso.getNome());
    }

    @Test
    public void naoDeveRetonarCursoQuandoConsultaPorNomeCujoNaoExista() {

        String nomeCurso = "Teste5";
        Curso curso = repository.findByNome(nomeCurso);

        Assert.assertNull(curso);
    }
}