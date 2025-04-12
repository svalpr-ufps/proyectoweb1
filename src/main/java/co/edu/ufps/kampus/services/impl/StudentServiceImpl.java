package co.edu.ufps.kampus.services.impl;

import co.edu.ufps.kampus.dtos.request.StudentRequest;
import co.edu.ufps.kampus.dtos.response.StudentResponse;
import co.edu.ufps.kampus.entities.Role;
import co.edu.ufps.kampus.entities.Student;
import co.edu.ufps.kampus.entities.User;
import co.edu.ufps.kampus.exceptions.ResourceNotFoundException;
import co.edu.ufps.kampus.repositories.RoleRepository;
import co.edu.ufps.kampus.repositories.StudentRepository;
import co.edu.ufps.kampus.services.StudentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

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
    public Optional<User> findByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public User update(UUID id, User userDetails) {
        return null;
    }

    @Override
    public void delete(UUID id) {

    }

    @Override
public StudentResponse registerStudent(StudentRequest request) {
    Student student = new Student();

    // Datos heredados de User
    student.setFirstName(request.getFirstName());
    student.setLastName(request.getLastName());
    student.setEmail(request.getEmail());
    student.setPhone(request.getPhone());
    student.setBirthDate(request.getBirthDate());
    student.setPasswordHash(passwordEncoder.encode(request.getPassword()));

    // Rol
    Role role = roleRepository.findById(request.getRoleId())
            .orElseThrow(() -> new ResourceNotFoundException("Rol no encontrado"));
    student.setRole(role);

    // Datos específicos de Student
    student.setStudentCode(request.getStudentCode());
    student.setEnrollmentDate(request.getEnrollmentDate());

    student = studentRepository.save(student);
    return mapToResponse(student);
}

@Override
public StudentResponse updateStudent(String studentCode, StudentRequest request) {
    Student student = studentRepository.findByStudentCode(studentCode)
            .orElseThrow(() -> new ResourceNotFoundException("Estudiante no encontrado con código: " + studentCode));

    student.setFirstName(request.getFirstName());
    student.setLastName(request.getLastName());
    student.setEmail(request.getEmail());
    student.setPhone(request.getPhone());
    student.setBirthDate(request.getBirthDate());

    if (request.getPassword() != null && !request.getPassword().isBlank()) {
        student.setPasswordHash(passwordEncoder.encode(request.getPassword()));
    }

    student.setEnrollmentDate(request.getEnrollmentDate());

    student = studentRepository.save(student);
    return mapToResponse(student);
}


    // ... otros métodos heredados
    private StudentResponse mapToResponse(Student student) {
        StudentResponse response = new StudentResponse();
        response.setId(student.getId());
        response.setFirstName(student.getFirstName());
        response.setLastName(student.getLastName());
        response.setEmail(student.getEmail());
        response.setPhone(student.getPhone());
        response.setBirthDate(student.getBirthDate());
        response.setStatus(student.getStatus().name());
        response.setStudentCode(student.getStudentCode());
        response.setEnrollmentDate(student.getEnrollmentDate());
        response.setRoleName(student.getRole().getName());
        return response;
    }
    
}