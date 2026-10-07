package com.eazybytes.springsecuritysection4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
//@EntityScan("com.eazybytes.springsecuritysection4.model")
//@EnableJpaRepositories("com.eazybytes.springsecuritysection4.repository")
public class EazyBankBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(EazyBankBackendApplication.class, args);
	}

}
