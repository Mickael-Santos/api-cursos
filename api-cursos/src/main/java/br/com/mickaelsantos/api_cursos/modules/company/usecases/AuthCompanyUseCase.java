package br.com.mickaelsantos.api_cursos.modules.company.usecases;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.com.mickaelsantos.api_cursos.exceptions.UsernameNotFoundException;
import br.com.mickaelsantos.api_cursos.modules.company.dtos.AuthCompanyDto;
import br.com.mickaelsantos.api_cursos.modules.company.dtos.AuthCompanyResponseDto;
import br.com.mickaelsantos.api_cursos.modules.company.repositories.CompanyRepository;

@Service
public class AuthCompanyUseCase
{
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${security.token.secret}")
    private String secretKey;

    @Value("${security.token.issuer}")
    private String issuer;

    public AuthCompanyResponseDto execute(AuthCompanyDto authCompanyDto) throws AuthenticationException
    {
        var company = companyRepository.findByUsername(authCompanyDto.getUsername())
        .orElseThrow(() -> 
        {
            throw new UsernameNotFoundException("Usuário não encontrado, username/password incorretos!");
        });
        
        var passwordMatches = passwordEncoder.matches(authCompanyDto.getPassword(), company.getPassword());

        if(!passwordMatches)
        {
            throw new AuthenticationException("credenciais incorretas!");
        }
        
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        var expiresIn = Instant.now().plus(Duration.ofHours(48));

        var token = JWT.create()
        .withIssuer(issuer)
        .withExpiresAt(expiresIn)
        .withClaim("roles", Arrays.asList("COMPANY"))
        .withSubject(company.getUuId().toString())
        .sign(algorithm);

        AuthCompanyResponseDto authCompanyResponseDto = AuthCompanyResponseDto.builder()
        .acess_token(token)
        .espiresIn(expiresIn.toEpochMilli())
        .build();

        return authCompanyResponseDto;
    }
}
