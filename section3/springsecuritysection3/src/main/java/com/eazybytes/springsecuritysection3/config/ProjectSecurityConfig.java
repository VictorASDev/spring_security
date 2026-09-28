package com.eazybytes.springsecuritysection3.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;

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
        http.formLogin(Customizer.withDefaults());
        http.httpBasic(Customizer.withDefaults());
        return (SecurityFilterChain)http.build();
    }


    // noop --> trata a senha como texto plano, sem nenhum tipo de criptografia
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withUsername("user").password("{noop}1234").authorities("READ").build();
        UserDetails admin = User.withUsername("admin").password("{bcrypt}$2a$12$uVdtgOV2PBnh2wJ0PPEQBu5ZoSsUcv95yBAizhKu/ePLyOdSxPBHW").authorities("ADMIN").build();

        return new InMemoryUserDetailsManager(user, admin);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public CompromisedPasswordChecker compromisedPasswordChecker() {
        return new HaveIBeenPwnedRestApiPasswordChecker();
    }
}

