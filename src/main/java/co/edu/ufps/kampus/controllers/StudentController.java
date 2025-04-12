package co.edu.ufps.kampus.controllers;

import co.edu.ufps.kampus.dtos.request.StudentRequest;
import co.edu.ufps.kampus.dtos.response.StudentResponse;
import co.edu.ufps.kampus.entities.Student;
import co.edu.ufps.kampus.services.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

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
    public ResponseEntity<StudentResponse> registerStudent(@RequestBody StudentRequest request) {
        StudentResponse response = studentService.registerStudent(request);
        return ResponseEntity.ok(response);
    }
    // Endpoint para actualizar datos personales de un estudiante
    @PutMapping("/{studentCode}")
    public ResponseEntity<StudentResponse> updateStudent(
            @PathVariable String studentCode,
            @RequestBody StudentRequest request
    ) {
        StudentResponse response = studentService.updateStudent(studentCode, request);
        return ResponseEntity.ok(response);
    }
}