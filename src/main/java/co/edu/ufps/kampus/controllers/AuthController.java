package co.edu.ufps.kampus.controllers;

import co.edu.ufps.kampus.dtos.request.LoginRequestDTO;
import co.edu.ufps.kampus.dtos.response.AuthResponseDTO;
import co.edu.ufps.kampus.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginRequestDTO request) {
        String token = authenticationService.authenticate(request.getEmail(), request.getPassword());
        return ResponseEntity.ok(new AuthResponseDTO(token));
    }
}