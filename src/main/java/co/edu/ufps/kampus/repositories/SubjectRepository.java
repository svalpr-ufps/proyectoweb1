package co.edu.ufps.kampus.repositories;

import co.edu.ufps.kampus.entities.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, UUID> {
    Optional<Subject> findByCode(String code);
    List<Subject> findByCourseId(UUID courseId);
    List<Subject> findByTeacherId(UUID teacherId);
    boolean existsByCode(String code);
}