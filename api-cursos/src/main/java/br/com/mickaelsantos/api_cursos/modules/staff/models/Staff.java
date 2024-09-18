package br.com.mickaelsantos.api_cursos.modules.staff.models;

import java.security.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity(name = "STAFF")
@Data
public class Staff 
{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuId;

    @NotBlank(message = "O campo [name] não pode ser vazio!")
    private String name;

    @Email(message = "O campo de [e-mail] conter um e-mail válido!")
    private String email;

    @NotBlank(message = "O campo [username] não pode ser vazio!")
    private String username;

    @Length(min = 10, max = 100, message = "O campo [password] deve conter no mínimo 10 caracteres e no máximo 100!")
    private String password;

    @CreationTimestamp
    private LocalDateTime created_at;
    
    @UpdateTimestamp
    private LocalDateTime updated_at;
}
