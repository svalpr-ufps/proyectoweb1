package co.edu.ufps.kampus.repositories;

import co.edu.ufps.kampus.entities.CourseAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CourseAssignmentRepository extends JpaRepository<CourseAssignment, UUID> {
}
