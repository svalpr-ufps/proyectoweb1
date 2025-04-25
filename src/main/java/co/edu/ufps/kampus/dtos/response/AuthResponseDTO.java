package co.edu.ufps.kampus.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponseDTO {
    private String token;
    private String fullName;
    private String role;


    public AuthResponseDTO(String token) {
    }

    public AuthResponseDTO(String jwt, String fullName, String email, String name) {
    }
}