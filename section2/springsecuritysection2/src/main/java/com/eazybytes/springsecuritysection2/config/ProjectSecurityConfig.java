package com.eazybytes.springsecuritysection2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) {
        /*
         http.authorizeHttpRequests((requests)
         -> ((AuthorizeHttpRequestsConfigurer.AuthorizedUrl)requests
         .anyRequest()).permitAll()
         .anyRequest()).denyAll();
        **/
        http.authorizeHttpRequests((requests)
                -> ((AuthorizeHttpRequestsConfigurer.AuthorizedUrl)requests
                .requestMatchers("/myAccount", "/myLoans", "/myCards").authenticated()
                .requestMatchers("/contact", "/notices", "/error")).permitAll());
        //http.formLogin(AbstractHttpConfigurer::disable); -> Desabilita segurança com formulário de login do spring security
        //http.httpBasic(AbstractHttpConfigurer::disable); -> Desabilita segurança com http basic
        http.httpBasic(Customizer.withDefaults());
        return (SecurityFilterChain)http.build();
    }
}

