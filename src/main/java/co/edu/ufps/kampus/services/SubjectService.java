package co.edu.ufps.kampus.services;

import co.edu.ufps.kampus.dtos.request.SubjectRequestDTO;
import co.edu.ufps.kampus.dtos.response.SubjectResponseDTO;
import co.edu.ufps.kampus.entities.Subject;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SubjectService {
    SubjectResponseDTO createSubject(SubjectRequestDTO request);
    SubjectResponseDTO getSubjectById(UUID id);
    List<SubjectResponseDTO> getAllSubjects();
    List<SubjectResponseDTO> getSubjectsByCourse(UUID courseId);
    SubjectResponseDTO updateSubject(UUID id, SubjectRequestDTO request);
    SubjectResponseDTO assignTeacher(UUID subjectId, UUID teacherId);

    Optional<Subject> findById(UUID id);
    Optional<Subject> findByCode(String code);
    Subject save(Subject subject);
    void delete(UUID id);

    List<Subject> findSubjectsByCourseId(UUID courseId);
}