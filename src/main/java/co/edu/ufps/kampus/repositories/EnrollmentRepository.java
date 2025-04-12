package co.edu.ufps.kampus.repositories;

import co.edu.ufps.kampus.entities.Enrollment;
import co.edu.ufps.kampus.entities.EnrollmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, UUID> {
    List<Enrollment> findByStudentId(UUID studentId);
    List<Enrollment> findBySubjectId(UUID subjectId);
    List<Enrollment> findByStatus(EnrollmentStatus status);
    boolean existsByStudentIdAndSubjectId(UUID studentId, UUID subjectId);
}