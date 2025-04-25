package co.edu.ufps.kampus.controllers;

import co.edu.ufps.kampus.services.CourseAssignmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/assignments")
public class CourseAssignmentController {

    private final CourseAssignmentService courseAssignmentService;

    public CourseAssignmentController(CourseAssignmentService courseAssignmentService) {
        this.courseAssignmentService = courseAssignmentService;
    }

    @PostMapping("/subject-to-teacher")
    public ResponseEntity<String> assignSubjectToTeacher(
            @RequestParam UUID subjectId,
            @RequestParam UUID teacherId) {
        courseAssignmentService.assignSubjectToTeacher(subjectId, teacherId);
        return ResponseEntity.ok("Subject assigned to teacher successfully.");
    }
}