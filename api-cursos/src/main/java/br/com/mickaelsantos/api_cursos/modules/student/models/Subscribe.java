package br.com.mickaelsantos.api_cursos.modules.student.models;

import java.time.LocalDateTime;
import java.util.UUID;

import br.com.mickaelsantos.api_cursos.modules.course.models.Course;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity(name = "TB_SUBCRIBE")
public class Subscribe 
{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuId;

    @ManyToOne()
    @JoinColumn(name = "course", insertable = false, updatable = false)
    private Course courseUuId;

    @Column(name = "course")
    private UUID course;

    @ManyToOne()
    @JoinColumn(name = "student", insertable = false, updatable = false)
    private Student studentUuId;

    @Column(name = "student")
    private UUID student;

    private LocalDateTime subscribed_at;
}
