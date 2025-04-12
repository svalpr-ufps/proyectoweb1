package co.edu.ufps.kampus.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class SubjectRequestDTO {
    @NotBlank(message = "El código de la materia es obligatorio")
    @Size(max = 20, message = "El código no puede exceder los 20 caracteres")
    private String code;

    @NotBlank(message = "El nombre de la materia es obligatorio")
    @Size(max = 100, message = "El nombre no puede exceder los 100 caracteres")
    private String name;

    @Size(max = 50, message = "El horario no puede exceder los 50 caracteres")
    private String schedule;

    @Size(max = 20, message = "El aula no puede exceder los 20 caracteres")
    private String classroom;

    private Integer capacity;

    @NotNull(message = "El curso es obligatorio")
    private UUID courseId;

    private UUID teacherId;

    private List<UUID> resourceIds;
}