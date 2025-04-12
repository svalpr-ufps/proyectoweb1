package co.edu.ufps.kampus.services;

import co.edu.ufps.kampus.entities.Teacher;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TeacherService extends UserService {
    Optional<Teacher> findByTeacherCode(String teacherCode);
    List<Teacher> findBySpecialization(String specialization);
    Teacher assignSubject(UUID teacherId, UUID subjectId);
}