package com.flerchante.recetario;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.Getter;

@SpringBootApplication
public class SaborApplication {

	@Getter
	private static final Logger LOGGER = LogManager.getLogger();

	public static void main(String[] args) {
		/* ApplicationContext context = SpringApplication.run(recetarioApplication.class, args); */
		SpringApplication.run(SaborApplication.class, args);
	}

}
