package co.edu.ufps.kampus.services.impl;

import co.edu.ufps.kampus.entities.Enrollment;
import co.edu.ufps.kampus.entities.EnrollmentStatus;
import co.edu.ufps.kampus.repositories.EnrollmentRepository;
import co.edu.ufps.kampus.services.EnrollmentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;

    @Override
    public Enrollment enrollStudent(UUID studentId, UUID subjectId) {
        Enrollment enrollment = new Enrollment();
        // Configurar student y subject
        return enrollmentRepository.save(enrollment);
    }

    @Override
    public Enrollment approveEnrollment(UUID enrollmentId) {
        return enrollmentRepository.findById(enrollmentId)
                .map(enrollment -> {
                    enrollment.setStatus(EnrollmentStatus.APPROVED);
                    return enrollmentRepository.save(enrollment);
                })
                .orElseThrow(() -> new RuntimeException("Enrollment not found"));
    }

    @Override
    public Enrollment rejectEnrollment(UUID enrollmentId, String reason) {
        return null;
    }

    @Override
    public List<Enrollment> findByStudentId(UUID studentId) {
        return enrollmentRepository.findByStudentId(studentId);
    }

    @Override
    public List<Enrollment> findBySubjectId(UUID subjectId) {
        return List.of();
    }

    @Override
    public List<Enrollment> findByStatus(EnrollmentStatus status) {
        return List.of();
    }

    // ... otros métodos
}