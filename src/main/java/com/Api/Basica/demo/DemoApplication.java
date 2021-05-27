package com.Api.Basica.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableSpringDataWebSupport // faz o spring pegar as informações de paginacao e ordenação do reqeust parameter
@SpringBootApplication
@EnableCaching // Ativar o sistema de cache
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
