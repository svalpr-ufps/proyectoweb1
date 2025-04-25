package co.edu.ufps.kampus.dtos.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.UUID;
@Getter
@Setter
public class StudentResponseDTO {

    private UUID id;

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private LocalDate birthDate;
    private String status;

    private String studentCode;
    private LocalDate enrollmentDate;

    private String roleName;

    // Constructor y/o Getters
}
