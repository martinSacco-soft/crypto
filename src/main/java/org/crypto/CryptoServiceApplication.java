package org.crypto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("org.crypto.repository")
public class CryptoServiceApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(CryptoServiceApplication.class, args);
	}

}
