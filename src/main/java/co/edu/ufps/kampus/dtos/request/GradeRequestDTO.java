package co.edu.ufps.kampus.dtos.request;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GradeRequestDTO {
    
    @NotBlank(message = "Student code is required")
    private String studentCode;
    
    @NotBlank(message = "Subject code is required")
    private String subjectCode;
    
    @NotBlank(message = "Course code is required")
    private String courseCode;

    @NotBlank(message = "Course name is required")
    private String courseName;

    @NotBlank(message = "Period is required")
    private String period;

    @DecimalMin(value = "0.0", message = "Grade must be at least 0.0")
    @DecimalMax(value = "5.0", message = "Grade cannot exceed 5.0")
    private Double value;
    
    private String comments; // Opcional
}