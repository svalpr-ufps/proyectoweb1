package co.edu.ufps.kampus.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Table(name = "schedules")
@Getter @Setter
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "classroom_id", nullable = false)
    private Classroom classroom;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private DayOfWeek day;
    
    @Column(nullable = false)
    private LocalTime startTime;
    
    @Column(nullable = false)
    private LocalTime endTime;
    
    // Validación de que la hora de fin es después de la de inicio
    @PrePersist
    @PreUpdate
    private void validateTimes() {
        if (startTime.isAfter(endTime)) {
            throw new IllegalArgumentException("La hora de inicio debe ser antes que la hora de fin");
        }
    }
}