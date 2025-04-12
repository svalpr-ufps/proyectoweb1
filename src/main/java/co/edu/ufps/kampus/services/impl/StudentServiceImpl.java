package co.edu.ufps.kampus.services.impl;

import co.edu.ufps.kampus.entities.Student;
import co.edu.ufps.kampus.entities.User;
import co.edu.ufps.kampus.repositories.StudentRepository;
import co.edu.ufps.kampus.services.StudentService;
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
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    // Métodos específicos de Student
    @Override
    public Optional<Student> findByStudentCode(String studentCode) {
        return studentRepository.findByStudentCode(studentCode);
    }

    @Override
    public List<Student> findByStatus(String status) {
        return studentRepository.findByStatus(status);
    }

    @Override
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    // Implementación de métodos heredados de UserService (CORREGIDOS)
    @Override
    public List<User> findAll() {
        return studentRepository.findAll()
                .stream()
                .map(student -> (User) student)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<User> findById(UUID id) {
        return studentRepository.findById(id)
                .map(student -> (User) student);
    }

    @Override
    public User save(User user) {
        if (!(user instanceof Student)) {
            throw new IllegalArgumentException("Solo se pueden guardar estudiantes");
        }
        return studentRepository.save((Student) user);
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

    // ... otros métodos heredados
}