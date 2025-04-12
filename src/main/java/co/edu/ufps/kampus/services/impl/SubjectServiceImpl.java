package co.edu.ufps.kampus.services.impl;

import co.edu.ufps.kampus.entities.Subject;
import co.edu.ufps.kampus.repositories.SubjectRepository;
import co.edu.ufps.kampus.services.SubjectService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;

    @Override
    public List<Subject> findAll() {
        return subjectRepository.findAll();
    }

    @Override
    public Optional<Subject> findById(UUID id) {
        return subjectRepository.findById(id);
    }

    @Override
    public Optional<Subject> findByCode(String code) {
        return subjectRepository.findByCode(code);
    }

    @Override
    public Subject save(Subject subject) {
        return subjectRepository.save(subject);
    }

    @Override
    public Subject update(UUID id, Subject subjectDetails) {
        return subjectRepository.findById(id)
                .map(subject -> {
                    subject.setName(subjectDetails.getName());
                    subject.setSchedule(subjectDetails.getSchedule());
                    // Actualizar otros campos
                    return subjectRepository.save(subject);
                })
                .orElseThrow(() -> new RuntimeException("Subject not found"));
    }

    @Override
    public void delete(UUID id) {
        subjectRepository.deleteById(id);
    }

    @Override
    public List<Subject> findByCourseId(UUID courseId) {
        return subjectRepository.findByCourseId(courseId);
    }

    @Override
    public Subject assignTeacher(UUID subjectId, UUID teacherId) {
        // Implementar lógica de asignación
        return subjectRepository.findById(subjectId).orElseThrow();
    }
}