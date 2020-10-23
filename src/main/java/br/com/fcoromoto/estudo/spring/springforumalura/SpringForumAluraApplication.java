package br.com.fcoromoto.estudo.spring.springforumalura;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport
@EnableCaching
public class SpringForumAluraApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringForumAluraApplication.class, args);
	}

}
