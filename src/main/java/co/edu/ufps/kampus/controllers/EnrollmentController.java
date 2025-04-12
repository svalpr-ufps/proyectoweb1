package co.edu.ufps.kampus.controllers;

import co.edu.ufps.kampus.entities.Enrollment;
import co.edu.ufps.kampus.services.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/enrollments")
@RequiredArgsConstructor
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    @PostMapping("/enroll")
    public ResponseEntity<Enrollment> enrollStudent(
            @RequestParam UUID studentId,
            @RequestParam UUID subjectId) {
        return ResponseEntity.ok(enrollmentService.enrollStudent(studentId, subjectId));
    }

    @PutMapping("/{enrollmentId}/approve")
    public ResponseEntity<Enrollment> approveEnrollment(@PathVariable UUID enrollmentId) {
        return ResponseEntity.ok(enrollmentService.approveEnrollment(enrollmentId));
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Enrollment>> getByStudent(@PathVariable UUID studentId) {
        return ResponseEntity.ok(enrollmentService.findByStudentId(studentId));
    }
}