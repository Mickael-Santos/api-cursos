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
    @Autowired
    private SecurityStudentFilter securityStudentFilter;
    @Autowired
    private SecurityStaffFilter securityStaffFilter;


    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {
        http.csrf( csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> {
            auth
            .requestMatchers("/company/create").permitAll()
            .requestMatchers("/company/auth/getToken").permitAll()
            .requestMatchers("/student/create").permitAll()
            .requestMatchers("/student/auth/getToken").permitAll()
            .requestMatchers("/staff/getToken").permitAll();

            auth.anyRequest().authenticated();
        })
        .addFilterBefore(securityCompanyFilter, BasicAuthenticationFilter.class)
        .addFilterBefore(securityStudentFilter, BasicAuthenticationFilter.class)
        .addFilterBefore(securityStaffFilter, BasicAuthenticationFilter.class);

        return http.build();
    }

    @Bean

    PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
}
