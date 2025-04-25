package co.edu.ufps.kampus.repositories;

import co.edu.ufps.kampus.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, UUID> {
    
    // Verifica solapamiento para aula (excluyendo el horario actual en updates)
    @Query("SELECT COUNT(s) > 0 FROM Schedule s WHERE " +
           "s.classroom.id = :classroomId AND s.day = :day AND " +
           "s.startTime < :endTime AND s.endTime > :startTime " +
           "AND (:excludeId IS NULL OR s.id != :excludeId)")
    boolean existsClassroomConflict(
        @Param("classroomId") UUID classroomId,
        @Param("day") DayOfWeek day,
        @Param("startTime") LocalTime startTime,
        @Param("endTime") LocalTime endTime,
        @Param("excludeId") UUID excludeId);
    
    // Verifica solapamiento para profesor (excluyendo el horario actual en updates)
    @Query("SELECT COUNT(s) > 0 FROM Schedule s WHERE " +
           "s.subject.teacher.id = :teacherId AND s.day = :day AND " +
           "s.startTime < :endTime AND s.endTime > :startTime " +
           "AND (:excludeId IS NULL OR s.id != :excludeId)")
    boolean existsTeacherConflict(
        @Param("teacherId") UUID teacherId,
        @Param("day") DayOfWeek day,
        @Param("startTime") LocalTime startTime,
        @Param("endTime") LocalTime endTime,
        @Param("excludeId") UUID excludeId);
    
    // Busca horarios por asignatura (con relaciones cargadas)
    @EntityGraph(attributePaths = {"classroom", "subject"})
    List<Schedule> findBySubjectId(UUID subjectId);
    
    // Busca horarios por aula (con relaciones cargadas)
    @EntityGraph(attributePaths = {"subject", "subject.teacher"})
    List<Schedule> findByClassroomId(UUID classroomId);
    
    // Busca horarios por profesor
    @Query("SELECT s FROM Schedule s WHERE s.subject.teacher.id = :teacherId")
    List<Schedule> findByTeacherId(@Param("teacherId") UUID teacherId);
}