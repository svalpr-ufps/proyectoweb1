package co.edu.ufps.kampus.controllers;

import co.edu.ufps.kampus.entities.Course;
import co.edu.ufps.kampus.services.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {
        return ResponseEntity.ok(courseService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable UUID id) {
        return courseService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(courseService.save(course));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(
            @PathVariable UUID id,
            @RequestBody Course courseDetails) {
        return ResponseEntity.ok(courseService.update(id, courseDetails));
    }

    @PostMapping("/{courseId}/prerequisites/{prerequisiteId}")
    public ResponseEntity<Course> addPrerequisite(
            @PathVariable UUID courseId,
            @PathVariable UUID prerequisiteId) {
        return ResponseEntity.ok(courseService.addPrerequisite(courseId, prerequisiteId));
    }
}