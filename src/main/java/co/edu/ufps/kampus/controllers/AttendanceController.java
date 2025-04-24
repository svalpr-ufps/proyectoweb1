package co.edu.ufps.kampus.controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import co.edu.ufps.kampus.entities.Student;
import co.edu.ufps.kampus.exceptions.ResourceNotFoundException;
import java.util.UUID;
import co.edu.ufps.kampus.services.StudentService;


import co.edu.ufps.kampus.entities.Attendance;
import co.edu.ufps.kampus.services.AttendanceService;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {
    @Autowired
    private AttendanceService attendanceService;

    @Autowired
    private StudentService studentService;

    @PostMapping("/register")
    public ResponseEntity<Attendance> registerAttendance(
            @RequestParam UUID studentId,
            @RequestParam boolean present,
            @RequestParam(required = false) String observations) {
        Student student = (Student) studentService.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + studentId));
        Attendance attendance = attendanceService.registerAttendance(student, present, observations);
        return ResponseEntity.ok(attendance);
    }

    @GetMapping("/report")
    public ResponseEntity<List<Attendance>> getReport(
            @RequestParam Long studentId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        Student student = null;
        // Implementar lógica para obtener el estudiante por ID
        List<Attendance> report = attendanceService.getAttendanceReport(student, startDate, endDate);
        return ResponseEntity.ok(report);
    }
}