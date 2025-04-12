package co.edu.ufps.kampus.services;

import co.edu.ufps.kampus.dtos.request.TeacherRegistrationDTO;
import co.edu.ufps.kampus.dtos.response.TeacherResponseDTO;
import co.edu.ufps.kampus.entities.Teacher;
import co.edu.ufps.kampus.entities.User;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TeacherService extends UserService {

    // Teacher-specific methods
    Optional<Teacher> findByTeacherCode(String teacherCode);

    List<Teacher> findBySpecialization(String specialization);

    Teacher assignSubject(UUID teacherId, UUID subjectId);

    // Teacher DTO operations
    TeacherResponseDTO registerTeacher(@Valid TeacherRegistrationDTO registrationDTO);

    TeacherResponseDTO getTeacherById(UUID id);

    TeacherResponseDTO getTeacherByCode(String teacherCode);

    TeacherResponseDTO updateTeacher(UUID id, @Valid TeacherRegistrationDTO updateDTO);

    TeacherResponseDTO assignSubjectToTeacher(UUID teacherId, UUID subjectId);

    List<TeacherResponseDTO> getTeachersBySpecialization(String specialization);

    // Inherited from UserService
    @Override
    List<User> findAll();

    @Override
    Optional<User> findById(UUID id);

    @Override
    User save(User user);

    @Override
    User update(UUID id, User userDetails);

    Optional<Teacher> findTeacherByEmail(String email);

    @Override
    void delete(UUID id);
}