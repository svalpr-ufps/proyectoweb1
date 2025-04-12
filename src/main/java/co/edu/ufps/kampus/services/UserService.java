package co.edu.ufps.kampus.services;

import co.edu.ufps.kampus.entities.User;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
    List<User> findAll();
    Optional<User> findById(UUID id);
    User save(User user);

    Optional<Object> findByEmail(String email);

    User update(UUID id, User userDetails);

    void delete(UUID id);
    // ... otros métodos
}