package co.edu.ufps.kampus.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Alert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;          // Ejemplo: "Examen parcial de Matemáticas"
    private String message;       // Detalle: "Recuerda que el examen es el 30/05"
    private LocalDateTime triggerDate; // Fecha/hora en que se dispara la alerta
    private boolean isRead;       // Para marcar como leída/no leída

    // Relaciones clave:
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;      // Destinatario (relación con Student)

    @ManyToOne
    @JoinColumn(name = "evaluation_id")
    private Evaluation evaluation; // Opcional: si la alerta es sobre un examen/tarea

    @Enumerated(EnumType.STRING)
    private AlertType type;       // Tipo: EXAM, TASK, GENERAL, etc.

    // Constructor, Getters y Setters
}