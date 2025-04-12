package co.edu.ufps.kampus.services;

import co.edu.ufps.kampus.entities.Course;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CourseService {
    List<Course> findAll();
    Optional<Course> findById(UUID id);
    Optional<Course> findByCode(String code);
    Course save(Course course);
    Course update(UUID id, Course course);
    void delete(UUID id);
    List<Course> findPrerequisites(UUID courseId);
    Course addPrerequisite(UUID courseId, UUID prerequisiteId);
}