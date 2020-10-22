package br.com.fcoromoto.estudo.spring.springforumalura;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport
public class SpringForumAluraApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringForumAluraApplication.class, args);
	}

}
