package co.edu.ufps.kampus.services;

import co.edu.ufps.kampus.entities.User;
import co.edu.ufps.kampus.entities.Role;
import co.edu.ufps.kampus.repositories.UserRepository;
import co.edu.ufps.kampus.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;
    @SuppressWarnings("unused")
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    @SuppressWarnings("unused")
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public String authenticate(String email, String password) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(email, password)
        );
        User user = (User) authentication.getPrincipal();
        return jwtService.generateToken(user);
    }

    @Transactional
    public void initializeRoles() {
        if (!roleRepository.existsByName("ROLE_STUDENT")) {
            Role studentRole = new Role();
            studentRole.setName("ROLE_STUDENT");
            studentRole.setDescription("Rol para estudiantes");
            roleRepository.save(studentRole);
        }

        if (!roleRepository.existsByName("ROLE_TEACHER")) {
            Role teacherRole = new Role();
            teacherRole.setName("ROLE_TEACHER");
            teacherRole.setDescription("Rol para profesores");
            roleRepository.save(teacherRole);
        }

        if (!roleRepository.existsByName("ROLE_ADMIN")) {
            Role adminRole = new Role();
            adminRole.setName("ROLE_ADMIN");
            adminRole.setDescription("Rol para administradores");
            roleRepository.save(adminRole);
        }
    }
}