package br.com.mickaelsantos.api_cursos.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig
{
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {
        http.csrf( csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> {
            auth.requestMatchers("/company/create").permitAll()
            .requestMatchers("/company/update/{uuid}").permitAll()
            .requestMatchers("/company/delete").permitAll()
            .requestMatchers("/company/get").permitAll()
            .requestMatchers("company/active/{uuid}").permitAll()
            .requestMatchers("/company/auth/getToken").permitAll()
            .requestMatchers("/student/create").permitAll()
            .requestMatchers("student/update").permitAll()
            .requestMatchers("student/get").permitAll()
            .requestMatchers("student/delete/{uuid}").permitAll()
            .requestMatchers("student/active/{uuid}/{active}").permitAll()
            .requestMatchers("/student/auth/getToken").permitAll()
            .requestMatchers("/category/create").permitAll();
            

            auth.anyRequest().authenticated();
        });

        return http.build();
    } 

    @Bean

    PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
}
