package co.edu.ufps.kampus.dtos.request;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class SubjectRequestDTO {
    @NotBlank(message = "Subject code is required")
    @Size(max = 20, message = "Subject code cannot exceed 20 characters")
    private String code;

    @NotBlank(message = "Subject name is required")
    @Size(max = 100, message = "Subject name cannot exceed 100 characters")
    private String name;

    @Size(max = 50, message = "Schedule cannot exceed 50 characters")
    private String schedule;

    @Size(max = 20, message = "Classroom cannot exceed 20 characters")
    private String classroom;

    @Min(value = 1, message = "Capacity must be at least 1")
    @Max(value = 100, message = "Capacity cannot exceed 100")
    private Integer capacity;

    @NotNull(message = "Course ID is required")
    private UUID courseId;

    private UUID teacherId;

    private List<UUID> resourceIds;
}