package co.edu.ufps.kampus.controllers;

import co.edu.ufps.kampus.entities.Subject;
import co.edu.ufps.kampus.services.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/subjects")
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectService subjectService;

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<Subject>> getByCourse(@PathVariable UUID courseId) {
        return ResponseEntity.ok(subjectService.findByCourseId(courseId));
    }

    @PostMapping("/{subjectId}/assign-teacher/{teacherId}")
    public ResponseEntity<Subject> assignTeacher(
            @PathVariable UUID subjectId,
            @PathVariable UUID teacherId) {
        return ResponseEntity.ok(subjectService.assignTeacher(subjectId, teacherId));
    }

    @GetMapping
    public ResponseEntity<List<Subject>> getAllSubjects() {
        return ResponseEntity.ok(subjectService.findAll());
    }

    @PostMapping
    public ResponseEntity<Subject> createSubject(@RequestBody Subject subject) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(subjectService.save(subject));
    }
}