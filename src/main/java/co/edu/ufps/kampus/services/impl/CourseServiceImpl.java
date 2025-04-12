package co.edu.ufps.kampus.services.impl;

import co.edu.ufps.kampus.entities.Course;
import co.edu.ufps.kampus.repositories.CourseRepository;
import co.edu.ufps.kampus.services.CourseService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public Optional<Course> findById(UUID id) {
        return courseRepository.findById(id);
    }

    @Override
    public Optional<Course> findByCode(String code) {
        return courseRepository.findByCode(code);
    }

    @Override
    public Course save(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Course update(UUID id, Course courseDetails) {
        return courseRepository.findById(id)
                .map(course -> {
                    course.setName(courseDetails.getName());
                    course.setDescription(courseDetails.getDescription());
                    // Actualizar otros campos
                    return courseRepository.save(course);
                })
                .orElseThrow(() -> new RuntimeException("Course not found"));
    }

    @Override
    public void delete(UUID id) {
        courseRepository.deleteById(id);
    }

    @Override
    public Set<Course> findPrerequisites(UUID courseId) {
        return courseRepository.findById(courseId)
                .map(Course::getPrerequisites)
                .orElseThrow(() -> new RuntimeException("Course not found"));
    }

    @Override
    public Course addPrerequisite(UUID courseId, UUID prerequisiteId) {
        Course course = courseRepository.findById(courseId).orElseThrow();
        Course prerequisite = courseRepository.findById(prerequisiteId).orElseThrow();
        course.getPrerequisites().add(prerequisite);
        return courseRepository.save(course);
    }
}