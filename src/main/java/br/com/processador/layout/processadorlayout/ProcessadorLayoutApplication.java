package br.com.processador.layout.processadorlayout;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Classe Main responsável pela inicialização do projeto
 * 
 * @author Marcelo Moraes
 *
 */
@SpringBootApplication
@EnableScheduling
public class ProcessadorLayoutApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProcessadorLayoutApplication.class, args);
	}
}
