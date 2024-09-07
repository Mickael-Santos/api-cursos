package br.com.mickaelsantos.api_cursos.modules.course.models;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import br.com.mickaelsantos.api_cursos.modules.company.models.Company;
import br.com.mickaelsantos.api_cursos.modules.student.models.Subscribe;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity(name = "course")
public class Course 
{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID  uuId;

    private String name;

    @ManyToOne()
    @JoinColumn(name = "category", insertable = false,updatable = false)
    private Category categoryUuId;

    @Column(name = "category")
    private UUID category;

    @ManyToOne()
    @JoinColumn(name = "company", insertable = false, updatable = false)
    private Company companyUuId;

    @Column(name = "company")
    private UUID company;

    private boolean active;

    @CreationTimestamp
    private LocalDateTime created_at;

    @UpdateTimestamp
    private LocalDateTime updated_at;

    @OneToMany(mappedBy = "course")
    private List<Subscribe> subscribes;
}
