package co.edu.ufps.kampus.services.impl;

import co.edu.ufps.kampus.dtos.request.TeacherRegistrationDTO;
import co.edu.ufps.kampus.dtos.response.TeacherResponseDTO;
import co.edu.ufps.kampus.entities.Subject;
import co.edu.ufps.kampus.entities.Teacher;
import co.edu.ufps.kampus.entities.User;
import co.edu.ufps.kampus.exceptions.ResourceNotFoundException;
import co.edu.ufps.kampus.repositories.SubjectRepository;
import co.edu.ufps.kampus.repositories.TeacherRepository;
import co.edu.ufps.kampus.services.TeacherService;
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
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final SubjectRepository subjectRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Optional<Teacher> findByTeacherCode(String teacherCode) {
        return Optional.empty();
    }

    @Override
    public List<Teacher> findBySpecialization(String specialization) {
        return List.of();
    }

    @Override
    public Teacher assignSubject(UUID teacherId, UUID subjectId) {
        return null;
    }

    @Override
    public TeacherResponseDTO registerTeacher(TeacherRegistrationDTO registrationDTO) {
        if (teacherRepository.existsByEmail(registrationDTO.getEmail())) {
            throw new IllegalStateException("Email already registered");
        }

        Teacher teacher = new Teacher();
        teacher.setFirstName(registrationDTO.getFirstName());
        teacher.setLastName(registrationDTO.getLastName());
        teacher.setEmail(registrationDTO.getEmail());
        teacher.setPasswordHash(passwordEncoder.encode(registrationDTO.getPassword()));
        teacher.setSpecialization(registrationDTO.getSpecialization());
        teacher.setTeacherCode(generateTeacherCode());

        Teacher savedTeacher = teacherRepository.save(teacher);
        return mapToResponseDTO(savedTeacher);
    }

    @Override
    public TeacherResponseDTO getTeacherById(UUID id) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found with ID: " + id));
        return mapToResponseDTO(teacher);
    }

    @Override
    public TeacherResponseDTO getTeacherByCode(String teacherCode) {
        Teacher teacher = teacherRepository.findByTeacherCode(teacherCode)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found with code: " + teacherCode));
        return mapToResponseDTO(teacher);
    }

    @Override
    public List<TeacherResponseDTO> getTeachersBySpecialization(String specialization) {
        List<Teacher> teachers = teacherRepository.findBySpecialization(specialization);
        return teachers.stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TeacherResponseDTO assignSubjectToTeacher(UUID teacherId, UUID subjectId) {
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found"));

        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new ResourceNotFoundException("Subject not found"));

        if (!teacher.getSubjects().contains(subject)) {
            teacher.getSubjects().add(subject);
            teacherRepository.save(teacher);
        }

        return mapToResponseDTO(teacher);
    }

    @Override
    public TeacherResponseDTO updateTeacher(UUID id, TeacherRegistrationDTO updateDTO) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found"));

        teacher.setFirstName(updateDTO.getFirstName());
        teacher.setLastName(updateDTO.getLastName());
        teacher.setSpecialization(updateDTO.getSpecialization());

        Teacher updatedTeacher = teacherRepository.save(teacher);
        return mapToResponseDTO(updatedTeacher);
    }

    // Other implemented methods from UserService
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
            throw new IllegalArgumentException("Only teachers can be saved");
        }
        return teacherRepository.save((Teacher) user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public User update(UUID id, User userDetails) {
        return teacherRepository.findById(id)
                .map(teacher -> {
                    teacher.setFirstName(userDetails.getFirstName());
                    teacher.setLastName(userDetails.getLastName());
                    return teacherRepository.save((Teacher) teacher);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found"));
    }

    @Override
    public Optional<Teacher> findTeacherByEmail(String email) {
        return Optional.empty();
    }


    @Override
    public void delete(UUID id) {
        teacherRepository.deleteById(id);
    }

    // Helper methods
    private TeacherResponseDTO mapToResponseDTO(Teacher teacher) {
        return TeacherResponseDTO.builder()
                .id(teacher.getId())
                .teacherCode(teacher.getTeacherCode())
                .firstName(teacher.getFirstName())
                .lastName(teacher.getLastName())
                .email(teacher.getEmail())
                .specialization(teacher.getSpecialization())
                .createdAt(teacher.getCreatedAt())
                .subjects(teacher.getSubjects().stream()
                        .map(subject -> TeacherResponseDTO.SubjectSimpleDTO.builder()
                                .id(subject.getId())
                                .code(subject.getCode())
                                .name(subject.getName())
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }

    private String generateTeacherCode() {
        String lastCode = teacherRepository.findTopByOrderByTeacherCodeDesc()
                .map(Teacher::getTeacherCode)
                .orElse("PROF-20220000");

        int number = Integer.parseInt(lastCode.split("-")[1]) + 1;
        return "PROF-" + String.format("%07d", number);
    }
}