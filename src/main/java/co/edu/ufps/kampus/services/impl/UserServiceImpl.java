package co.edu.ufps.kampus.services.impl;

import co.edu.ufps.kampus.entities.User;
import co.edu.ufps.kampus.repositories.UserRepository;
import co.edu.ufps.kampus.services.UserService;
import co.edu.ufps.kampus.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Primary
@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll(); // No necesita conversión
    }

    @Override
    public Optional<User> findById(UUID id) {
        return userRepository.findById(id);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<Object> findByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public User update(UUID id, User userDetails) {
        return null;
    }

    @Override
    public void delete(UUID id) {

    }
    // ... otros métodos
}