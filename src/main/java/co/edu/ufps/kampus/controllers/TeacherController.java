package co.edu.ufps.kampus.controllers;

import co.edu.ufps.kampus.dtos.request.TeacherRegistrationDTO;
import co.edu.ufps.kampus.dtos.response.TeacherResponseDTO;
import co.edu.ufps.kampus.services.TeacherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/teachers")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    @PostMapping("/register")
    public ResponseEntity<TeacherResponseDTO> registerTeacher(
            @Valid @RequestBody TeacherRegistrationDTO registrationDTO) {
        TeacherResponseDTO response = teacherService.registerTeacher(registrationDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeacherResponseDTO> updateTeacher(
            @PathVariable UUID id,
            @Valid @RequestBody TeacherRegistrationDTO updateDTO) {
        TeacherResponseDTO response = teacherService.updateTeacher(id, updateDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherResponseDTO> getTeacherById(@PathVariable UUID id) {
        return ResponseEntity.ok(teacherService.getTeacherById(id));
    }

    @GetMapping("/code/{teacherCode}")
    public ResponseEntity<TeacherResponseDTO> getTeacherByCode(@PathVariable String teacherCode) {
        return ResponseEntity.ok(teacherService.getTeacherByCode(teacherCode));
    }

    @GetMapping("/specialization/{specialization}")
    public ResponseEntity<List<TeacherResponseDTO>> getTeachersBySpecialization(
            @PathVariable String specialization) {
        return ResponseEntity.ok(teacherService.getTeachersBySpecialization(specialization));
    }

    @PostMapping("/{teacherId}/assign-subject/{subjectId}")
    public ResponseEntity<TeacherResponseDTO> assignSubjectToTeacher(
            @PathVariable UUID teacherId,
            @PathVariable UUID subjectId) {
        return ResponseEntity.ok(teacherService.assignSubjectToTeacher(teacherId, subjectId));
    }
}