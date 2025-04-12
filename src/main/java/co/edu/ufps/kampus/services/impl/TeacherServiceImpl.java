package co.edu.ufps.kampus.services.impl;

import co.edu.ufps.kampus.entities.Teacher;
import co.edu.ufps.kampus.entities.User;
import co.edu.ufps.kampus.repositories.TeacherRepository;
import co.edu.ufps.kampus.services.TeacherService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;

    // Métodos específicos de Teacher
    @Override
    public Optional<Teacher> findByTeacherCode(String teacherCode) {
        return teacherRepository.findByTeacherCode(teacherCode);
    }

    @Override
    public List<Teacher> findBySpecialization(String specialization) {
        return List.of();
    }

    @Override
    public Teacher assignSubject(UUID teacherId, UUID subjectId) {
        return null;
    }

    // Implementación de métodos heredados de UserService (CORREGIDOS)
    @Override
    public List<User> findAll() {
        return teacherRepository.findAll()
                .stream()
                .map(teacher -> (User) teacher)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<User> findById(UUID id) {
        return teacherRepository.findById(id)
                .map(teacher -> (User) teacher);
    }

    @Override
    public User save(User user) {
        if (!(user instanceof Teacher)) {
            throw new IllegalArgumentException("Solo se pueden guardar profesores");
        }
        return teacherRepository.save((Teacher) user);
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