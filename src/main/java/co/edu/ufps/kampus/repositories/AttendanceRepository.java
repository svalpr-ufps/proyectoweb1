package co.edu.ufps.kampus.repositories;

import co.edu.ufps.kampus.entities.Attendance;
import co.edu.ufps.kampus.entities.Student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, UUID> {
    List<Attendance> findByStudentId(UUID studentId);
    List<Attendance> findBySubjectId(UUID subjectId);
    List<Attendance> findBySubjectIdAndDate(UUID subjectId, LocalDate date);
    List<Attendance> findByDateTime(LocalDateTime dateTime);
    List<Attendance> findByStudentAndDateTimeBetween(Student student, LocalDateTime startDate, LocalDateTime endDate);
}