package co.edu.ufps.kampus.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "academic_resources")
@Getter @Setter
public class AcademicResource {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ResourceType type;

    @Column(length = 100)
    private String title;

    @Column(length = 255)
    private String url;

    private boolean available = true;

    @ManyToMany(mappedBy = "resource")
    private List<Subject> subjects = new ArrayList<>();

    @OneToMany(mappedBy = "resource", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reservation> reservations = new ArrayList<>();

    @OneToMany(mappedBy = "resource", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SubjectResource> subjectAssociations = new ArrayList<>();
}