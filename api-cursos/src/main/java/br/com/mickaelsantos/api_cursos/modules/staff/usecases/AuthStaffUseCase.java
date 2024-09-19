package br.com.mickaelsantos.api_cursos.modules.staff.usecases;

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

import br.com.mickaelsantos.api_cursos.exceptions.StaffNofFoundException;
import br.com.mickaelsantos.api_cursos.modules.staff.dtos.AuthStaffRequestDto;
import br.com.mickaelsantos.api_cursos.modules.staff.dtos.AuthStaffResponseDto;
import br.com.mickaelsantos.api_cursos.modules.staff.repositories.StaffRepository;

@Service
public class AuthStaffUseCase 
{

    @Value("${security.staff.secret}")
    private String secretKey;
    @Value("${security.api.issuer}")
    private String issuer;
    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthStaffResponseDto execute(AuthStaffRequestDto credentials) throws AuthenticationException
    {
        var staff = staffRepository.findByUsername(credentials.getUsername())
        .orElseThrow(() -> {
            throw new StaffNofFoundException();
        });

        var passwordMatcher = passwordEncoder.matches(credentials.getPassword(), staff.getPassword());

        if(!passwordMatcher)
        {
            throw new AuthenticationException("credenciais incorretas!");
        }

        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        
        var expiresIn = Instant.now().plus(Duration.ofHours(48));
        var token = JWT.create()
        .withIssuer(issuer)
        .withExpiresAt(expiresIn)
        .withClaim("roles", Arrays.asList("STAFF"))
        .withSubject(staff.getUuId().toString())
        .sign(algorithm);

        AuthStaffResponseDto responseDTO = AuthStaffResponseDto.builder()
        .token(token)
        .expiresIn(expiresIn.toEpochMilli())
        .build();

        return responseDTO;

    }
}
