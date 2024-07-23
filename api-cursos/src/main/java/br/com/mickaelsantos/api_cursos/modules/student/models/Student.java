package br.com.mickaelsantos.api_cursos.modules.student.models;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity(name = "TB_STUDENT")
public class Student 
{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuId;

    @NotBlank(message = "O campo [name] não pode ser vazio!")
    private String name;

    @Pattern(regexp = "^\\d+$", message = "O campo [cpf] não pode conter espaços!")
    private String cpf;

    @NotBlank(message = "O campo [email] não pode ser vazio!")
    @Email(message = "O campo [email] deve conter um e-mail válido!")
    private String email;

    @NotBlank(message = "O campo [username] não ser vazio!")
    private String username;

    @Length(min = 10, max = 100, message = "O campo [password] deve conter entre 10 e 100 caracteres!")
    private String password;

    @CreationTimestamp
    private LocalDateTime created_at;

    @UpdateTimestamp
    private LocalDateTime updated_at;

    @OneToMany(mappedBy = "student")
    private List<Subscribe> subscribes;
}


