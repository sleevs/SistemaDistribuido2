package br.com.jsncartoesms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class JsnCartoesMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(JsnCartoesMsApplication.class, args);
	}

}
