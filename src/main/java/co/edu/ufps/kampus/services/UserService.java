package co.edu.ufps.kampus.services;

import co.edu.ufps.kampus.entities.User;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
    List<User> findAll();
    Optional<User> findById(UUID id);
    User save(User user);
    // ... otros métodos
}