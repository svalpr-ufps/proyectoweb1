package co.edu.ufps.kampus.services;

import co.edu.ufps.kampus.entities.Student;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StudentService extends UserService {
    Optional<Student> findByStudentCode(String studentCode);
    List<Student> findByStatus(String status);
    Student createStudent(Student student);
}