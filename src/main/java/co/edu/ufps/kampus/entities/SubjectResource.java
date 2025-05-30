package co.edu.ufps.kampus.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "subject_resources", indexes = {
        @Index(name = "idx_subject_resource_subject", columnList = "subject_id"),
        @Index(name = "idx_subject_resource_resource", columnList = "resource_id")
})
@Getter
@Setter
public class SubjectResource {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    @ManyToOne
    @JoinColumn(name = "resource_id", nullable = false)
    private AcademicResource resource;

    // Atributos adicionales si los necesitas
    @Column(name = "assigned_date")
    private LocalDateTime assignedDate;
}