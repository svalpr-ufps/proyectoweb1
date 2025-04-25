package co.edu.ufps.kampus.services;

import co.edu.ufps.kampus.dtos.request.StudentRequestDTO;
import co.edu.ufps.kampus.dtos.response.StudentResponseDTO;
import co.edu.ufps.kampus.entities.Student;
import java.util.List;
import java.util.Optional;

public interface StudentService extends UserService {
    Optional<Student> findByStudentCode(String studentCode);
    List<Student> findByStatus(String status);
    Student createStudent(Student student);
    StudentResponseDTO registerStudent(StudentRequestDTO request);
    StudentResponseDTO updateStudent(String studentCode, StudentRequestDTO request);
}
