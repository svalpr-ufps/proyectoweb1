package co.edu.ufps.kampus.repositories;

import co.edu.ufps.kampus.entities.Subject;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, UUID> {

    boolean existsByCode(String code);

    Optional<Subject> findByCode(String code);

    @EntityGraph(attributePaths = {"course", "teacher", "resources"})
    Optional<Subject> findWithDetailsById(UUID id);

    List<Subject> findByCourseId(UUID courseId);

    @Query("SELECT s FROM Subject s WHERE s.teacher.id = :teacherId")
    List<Subject> findByTeacherId(@Param("teacherId") UUID teacherId);

    @Query("SELECT s FROM Subject s JOIN s.resources r WHERE r.id = :resourceId")
    List<Subject> findByResourceId(@Param("resourceId") UUID resourceId);

    @EntityGraph(attributePaths = {"teacher", "course"})
    List<Subject> findByTeacherIdAndCourseId(UUID teacherId, UUID courseId);
}