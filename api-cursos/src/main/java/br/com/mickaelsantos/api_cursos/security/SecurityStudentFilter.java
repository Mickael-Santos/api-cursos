package br.com.mickaelsantos.api_cursos.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.mickaelsantos.api_cursos.providers.JWTStudentProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityStudentFilter extends OncePerRequestFilter
{

    @Autowired
    private JWTStudentProvider jwtStudentProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        var headerToken = request.getHeader("Authorization");
        
        if(request.getRequestURI().startsWith("/student"))
        {

            if(headerToken != null)
            {
                var token = jwtStudentProvider.validateToken(headerToken);
            
                if(token == null)
                {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    return;
                }
    
                request.setAttribute("student_uuid", token.getSubject());
    
                var roles = token.getClaim("roles").asList(Object.class);
    
                var granteds = roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_"+ role.toString().toUpperCase()))
                .toList();
    
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                    token.getSubject(),
                    null,
                    granteds);
    
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
            else
            {
                System.out.println("A header do token é atualmente nula!");
            }
           
        }

        filterChain.doFilter(request, response);
    }
    
}
