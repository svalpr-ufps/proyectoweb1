package co.edu.ufps.kampus.services;

import co.edu.ufps.kampus.entities.Subject;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SubjectService {
    List<Subject> findAll();
    Optional<Subject> findById(UUID id);
    Optional<Subject> findByCode(String code);
    Subject save(Subject subject);
    Subject update(UUID id, Subject subject);
    void delete(UUID id);
    List<Subject> findByCourseId(UUID courseId);
    Subject assignTeacher(UUID subjectId, UUID teacherId);
}