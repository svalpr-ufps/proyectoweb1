package co.edu.ufps.kampus.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import javax.tools.Diagnostic;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "grades")
@Getter @Setter
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private Double score;

    private LocalDate evaluationDate;

    @Column(columnDefinition = "TEXT")
    private String comments;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "academic_record_id", nullable = false)
    private AcademicRecord academicRecord;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "evaluation_id", nullable = false)
    private Evaluation evaluation;

    public Diagnostic<Object> getCourse() {
        return null;
    }

    public @NotBlank(message = "Period is required") String getPeriod() {
    return null;
    }

    public @DecimalMin(value = "0.0", message = "Grade value must be at least 0.0") @DecimalMax(value = "5.0", message = "Grade value cannot exceed 5.0") Double getValue() {
        return null;
    }
}