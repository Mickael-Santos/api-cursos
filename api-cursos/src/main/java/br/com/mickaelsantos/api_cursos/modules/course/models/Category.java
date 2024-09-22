package br.com.mickaelsantos.api_cursos.modules.course.models;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity(name = "category")
public class Category 
{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuId;

    private String name;
 
    private boolean active;

    @OneToMany(mappedBy = "category")
    private List<Course> courses;
}
