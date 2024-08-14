package br.com.mickaelsantos.api_cursos.modules.student.usecases;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.com.mickaelsantos.api_cursos.exceptions.StudentNotFoundException;
import br.com.mickaelsantos.api_cursos.modules.student.dto.AuthStudentRequestDto;
import br.com.mickaelsantos.api_cursos.modules.student.dto.AuthStudentResponseDto;
import br.com.mickaelsantos.api_cursos.modules.student.repositories.StudentRepository;

@Service
public class AuthStudentUseCase 
{
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${security.student.secret}")
    private String secretKey;

    @Value("${security.api.issuer}")
    private String issuer;

    public AuthStudentResponseDto execute(AuthStudentRequestDto authStudentDTO) throws AuthenticationException
    {
        var student = studentRepository.findByUsername(authStudentDTO.getUsername())
        .orElseThrow(() -> {
            throw new StudentNotFoundException();
        });

        var passwordMatches = passwordEncoder.matches(authStudentDTO.getPassword(), student.getPassword());

        if(!passwordMatches)
        {
            throw new AuthenticationException("credenciais inválidas");
        }

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        var expiresIn = Instant.now().plus(Duration.ofHours(48));

        var token = JWT.create()
        .withIssuer(issuer)
        .withExpiresAt(expiresIn)
        .withClaim("roles", Arrays.asList("STUDENT"))
        .withSubject(student.getUuId().toString())
        .sign(algorithm);

        AuthStudentResponseDto authStudentResponseDTO = AuthStudentResponseDto.builder()
        .token(token)
        .expiresIn(expiresIn.toEpochMilli())
        .build();

        return authStudentResponseDTO;
    }
}
