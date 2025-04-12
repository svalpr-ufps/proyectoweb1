package co.edu.ufps.kampus.services;

import co.edu.ufps.kampus.entities.Enrollment;
import co.edu.ufps.kampus.entities.EnrollmentStatus;
import java.util.List;
import java.util.UUID;

public interface EnrollmentService {
    Enrollment enrollStudent(UUID studentId, UUID subjectId);
    Enrollment approveEnrollment(UUID enrollmentId);
    Enrollment rejectEnrollment(UUID enrollmentId, String reason);
    List<Enrollment> findByStudentId(UUID studentId);
    List<Enrollment> findBySubjectId(UUID subjectId);
    List<Enrollment> findByStatus(EnrollmentStatus status);
}