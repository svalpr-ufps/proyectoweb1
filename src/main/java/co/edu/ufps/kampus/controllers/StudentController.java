package co.edu.ufps.kampus.controllers;

import co.edu.ufps.kampus.dtos.request.StudentRequestDTO;
import co.edu.ufps.kampus.dtos.response.StudentResponseDTO;
import co.edu.ufps.kampus.entities.Student;
import co.edu.ufps.kampus.services.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/code/{studentCode}")
    public ResponseEntity<Student> getByStudentCode(@PathVariable String studentCode) {
        return studentService.findByStudentCode(studentCode)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Student>> getByStatus(@PathVariable String status) {
        return ResponseEntity.ok(studentService.findByStatus(status));
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student savedStudent = studentService.createStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedStudent);
    }

    // Puedes agregar endpoints específicos para estudiantes aquí
    
    // Endpoint para registrar estudiante
    @PostMapping
    public ResponseEntity<StudentResponseDTO> registerStudent(@RequestBody StudentRequestDTO request) {
        StudentResponseDTO response = studentService.registerStudent(request);
        return ResponseEntity.ok(response);
    }
    // Endpoint para actualizar datos personales de un estudiante
    @PutMapping("/{studentCode}")
    public ResponseEntity<StudentResponseDTO> updateStudent(
            @PathVariable String studentCode,
            @RequestBody StudentRequestDTO request
    ) {
        StudentResponseDTO response = studentService.updateStudent(studentCode, request);
        return ResponseEntity.ok(response);
    }
}