package co.edu.ufps.kampus.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "classrooms")
@Getter @Setter
public class Classroom {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @Column(unique = true, nullable = false, length = 20)
    private String code;
    
    @Column(nullable = false, length = 50)
    private String name;
    
    @Column(nullable = false)
    private Integer capacity;
    
    @Column(length = 50)
    private String building;
    
    @Column(length = 100)
    private String facilities; // Ej: "Proyector, Computadoras, Aire acondicionado"
    
    @OneToMany(mappedBy = "classroom", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Schedule> schedules = new ArrayList<>();
}