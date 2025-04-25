package co.edu.ufps.kampus.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ufps.kampus.entities.Attendance;
import co.edu.ufps.kampus.entities.Student;
import co.edu.ufps.kampus.repositories.AttendanceRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class AttendanceService {
    @Autowired
    private AttendanceRepository attendanceRepository;

    public Attendance registerAttendance(Student student, boolean present, String observations) {
        Attendance attendance = new Attendance();
        attendance.setStudent(student);
        attendance.setDateTime(LocalDateTime.now());
        attendance.setPresent(present);
        attendance.setObservations(observations);
        return attendanceRepository.save(attendance);
    }

    public List<Attendance> getAttendanceReport(Student student, LocalDateTime startDate, LocalDateTime endDate) {
        return attendanceRepository.findByStudentAndDateTimeBetween(student, startDate, endDate);
    }

    public List<Attendance> getDailyAttendance(LocalDateTime date) {
        return attendanceRepository.findByDateTime(date);
    }
}