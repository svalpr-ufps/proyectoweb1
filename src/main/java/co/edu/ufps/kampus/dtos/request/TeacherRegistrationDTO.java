package co.edu.ufps.kampus.dtos.request;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TeacherRegistrationDTO {

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 50, message = "Máximo 50 caracteres")
    private String firstName;

    @NotBlank(message = "El apellido es obligatorio")
    @Size(max = 50, message = "Máximo 50 caracteres")
    private String lastName;

    @Email(message = "Debe ser un email válido")
    @NotBlank(message = "El email es obligatorio")
    private String email;

    @Size(min = 8, message = "Mínimo 8 caracteres")
    private String password;

    @NotBlank(message = "La especialización es requerida")
    private String specialization;
}