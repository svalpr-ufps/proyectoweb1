package co.edu.ufps.kampus.repositories;

import co.edu.ufps.kampus.entities.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface GradeRepository extends JpaRepository<Grade, UUID> {
    List<Grade> findByAcademicRecordId(UUID academicRecordId);
    List<Grade> findByEvaluationId(UUID evaluationId);
    List<Grade> findByAcademicRecord_Student_Id(UUID studentId);
    List<Grade> findByAcademicRecord_Student_IdAndEvaluation_DateBetween(UUID studentId, LocalDate startDate, LocalDate endDate); // ✅ Added
}
