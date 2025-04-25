package co.edu.ufps.kampus.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "rooms")
@Getter
@Setter
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true, nullable = false, length = 20)
    private String code; // Ej: "A-101" o "LAB-1"

    @Enumerated(EnumType.STRING)
    private RoomType type; // Enum: CLASSROOM, LABORATORY, AUDITORIUM

    private Integer capacity;

    @Column(name = "has_projector")
    private boolean hasProjector;

    @Enumerated(EnumType.STRING)
    private RoomStatus status; // Enum: AVAILABLE, UNDER_MAINTENANCE, RESERVED

    // Relación opcional con recursos académicos (si necesitas más detalles)
    @ManyToMany(mappedBy = "rooms")
    private List<AcademicResource> resources = new ArrayList<>();
}
