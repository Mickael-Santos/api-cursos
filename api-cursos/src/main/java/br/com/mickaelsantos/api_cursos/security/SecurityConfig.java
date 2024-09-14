package br.com.mickaelsantos.api_cursos.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig
{
    @Autowired
    private SecurityCompanyFilter securityCompanyFilter;


    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {
        http.csrf( csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> {
            auth
            .requestMatchers("/company/create").permitAll()
            .requestMatchers("/company/auth/getToken").permitAll()
            .requestMatchers("/student/create").permitAll()
            .requestMatchers("student/update").permitAll()
            .requestMatchers("student/get").permitAll()
            .requestMatchers("student/delete/{uuid}").permitAll()
            .requestMatchers("student/active/{uuid}/{active}").permitAll()
            .requestMatchers("/student/auth/getToken").permitAll()
            .requestMatchers("/category/create").permitAll()
            .requestMatchers("/category/update/{uuid}").permitAll()
            .requestMatchers("/category/get").permitAll()
            .requestMatchers("/category/toggle/{uuid}").permitAll()
            .requestMatchers("/course/create").permitAll()
            .requestMatchers("/course/update/{uuid}").permitAll()
            .requestMatchers("/course/get").permitAll()
            .requestMatchers("/course/delete/{uuid}").permitAll()
            .requestMatchers("course/toggle/{uuid}").permitAll();

            auth.anyRequest().authenticated();
        })
        .addFilterBefore(securityCompanyFilter, BasicAuthenticationFilter.class);

        return http.build();
    } 

    @Bean

    PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
}
