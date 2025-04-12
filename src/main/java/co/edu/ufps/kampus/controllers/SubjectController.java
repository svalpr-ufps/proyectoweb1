package co.edu.ufps.kampus.controllers;

import co.edu.ufps.kampus.dtos.request.SubjectRequestDTO;
import co.edu.ufps.kampus.dtos.response.SubjectResponseDTO;
import co.edu.ufps.kampus.services.SubjectService;
import jakarta.validation.Valid;
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

    @PostMapping
    public ResponseEntity<SubjectResponseDTO> createSubject(
            @Valid @RequestBody SubjectRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(subjectService.createSubject(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubjectResponseDTO> getSubjectById(@PathVariable UUID id) {
        return ResponseEntity.ok(subjectService.getSubjectById(id));
    }

    @GetMapping
    public ResponseEntity<List<SubjectResponseDTO>> getAllSubjects() {
        return ResponseEntity.ok(subjectService.getAllSubjects());
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<SubjectResponseDTO>> getSubjectsByCourse(
            @PathVariable UUID courseId) {
        return ResponseEntity.ok(subjectService.getSubjectsByCourse(courseId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubjectResponseDTO> updateSubject(
            @PathVariable UUID id,
            @Valid @RequestBody SubjectRequestDTO request) {
        return ResponseEntity.ok(subjectService.updateSubject(id, request));
    }

    @PostMapping("/{subjectId}/assign-teacher/{teacherId}")
    public ResponseEntity<SubjectResponseDTO> assignTeacher(
            @PathVariable UUID subjectId,
            @PathVariable UUID teacherId) {
        return ResponseEntity.ok(subjectService.assignTeacher(subjectId, teacherId));
    }
}