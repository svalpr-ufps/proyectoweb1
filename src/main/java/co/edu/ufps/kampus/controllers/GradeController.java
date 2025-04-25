package co.edu.ufps.kampus.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.ufps.kampus.dtos.request.GradeRequestDTO;
import co.edu.ufps.kampus.dtos.response.GradeResponseDTO;
import co.edu.ufps.kampus.services.GradeService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/grades")
public class GradeController {

    @Autowired
    private GradeService gradeService;

    // Registrar una nueva calificación
    @PostMapping
    public ResponseEntity<GradeResponseDTO> registerGrade(@Valid @RequestBody GradeRequestDTO request) {
       
        return ResponseEntity.ok(gradeService.registerGrade(request));
    }

    // Obtener calificaciones por estudiante
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<GradeResponseDTO>> getGradesByStudent(@PathVariable UUID studentId) {
        return ResponseEntity.ok(gradeService.getGradesByStudentId(studentId));
    }
}