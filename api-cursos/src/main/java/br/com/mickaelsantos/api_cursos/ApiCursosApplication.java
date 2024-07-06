package br.com.mickaelsantos.api_cursos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("br")
public class ApiCursosApplication {

	public static void main(String[] args) 
	{
		SpringApplication.run(ApiCursosApplication.class, args);
	}

}
