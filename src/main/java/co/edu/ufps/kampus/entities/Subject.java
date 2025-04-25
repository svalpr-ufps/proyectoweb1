package co.edu.ufps.kampus.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "subjects")
@Getter @Setter
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true, nullable = false, length = 20)
    private String code;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 50)
    private String schedule;

    private String title;

    @Column(length = 20)
    private String classroom;

    private Integer capacity;
    private Integer credits;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Enrollment> enrollments = new ArrayList<>();

    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Evaluation> evaluations = new ArrayList<>();

    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Attendance> attendances = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "subject_resources",
            joinColumns = @JoinColumn(name = "subject_id"),
            inverseJoinColumns = @JoinColumn(name = "resource_id"),
            foreignKey = @ForeignKey(name = "fk_subject_resource_subject"),
            inverseForeignKey = @ForeignKey(name = "fk_subject_resource_resource")
    )
    private List<AcademicResource> resources = new ArrayList<>();

    // Métodos helper para manejar la relación bidireccional
    public void addResource(AcademicResource resource) {
        this.resources.add(resource);
        resource.getSubjects().add(this);
    }

    public void removeResource(AcademicResource resource) {
        this.resources.remove(resource);
        resource.getSubjects().remove(this);
    }


}