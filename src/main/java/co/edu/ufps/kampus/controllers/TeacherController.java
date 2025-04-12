package co.edu.ufps.kampus.controllers;

import co.edu.ufps.kampus.entities.Teacher;
import co.edu.ufps.kampus.services.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/teachers")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    @GetMapping("/code/{teacherCode}")
    public ResponseEntity<Teacher> getByTeacherCode(@PathVariable String teacherCode) {
        return teacherService.findByTeacherCode(teacherCode)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/specialization/{specialization}")
    public ResponseEntity<List<Teacher>> getBySpecialization(@PathVariable String specialization) {
        return ResponseEntity.ok(teacherService.findBySpecialization(specialization));
    }

    @PostMapping("/{teacherId}/assign-subject/{subjectId}")
    public ResponseEntity<Teacher> assignSubject(
            @PathVariable UUID teacherId,
            @PathVariable UUID subjectId) {
        return ResponseEntity.ok(teacherService.assignSubject(teacherId, subjectId));
    }
}