package br.com.mickaelsantos.api_cursos.modules.student.models;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity(name = "TB_STUDENT")
public class Student 
{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuId;

    private String name;

    private String cpf;

    private String username;

    private String password;

    @CreationTimestamp
    private LocalDateTime created_at;

    @UpdateTimestamp
    private LocalDateTime updated_at;

    @OneToMany(mappedBy = "student")
    private List<Subscribe> subscribes;
}
