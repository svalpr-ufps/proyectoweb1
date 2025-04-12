package co.edu.ufps.kampus.dtos.request;

import java.time.LocalDate;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentRequest {

    // Campos heredados de User
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private LocalDate birthDate;
    private String password;
    private UUID roleId;

    // Campos propios de Student
    private String studentCode;
    private LocalDate enrollmentDate;

    // Getters y Setters
}
