package co.edu.ufps.kampus.services;

import co.edu.ufps.kampus.entities.Subject;
import co.edu.ufps.kampus.entities.Teacher;
import co.edu.ufps.kampus.entities.CourseAssignment;
import co.edu.ufps.kampus.repositories.SubjectRepository;
import co.edu.ufps.kampus.repositories.TeacherRepository;
import co.edu.ufps.kampus.repositories.CourseAssignmentRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CourseAssignmentService {

    private final SubjectRepository subjectRepository;
    private final TeacherRepository teacherRepository;
    private final CourseAssignmentRepository courseAssignmentRepository;

    public CourseAssignmentService(SubjectRepository subjectRepository,
                                   TeacherRepository teacherRepository,
                                   CourseAssignmentRepository courseAssignmentRepository) {
        this.subjectRepository = subjectRepository;
        this.teacherRepository = teacherRepository;
        this.courseAssignmentRepository = courseAssignmentRepository;
    }

    public void assignSubjectToTeacher(UUID subjectId, UUID teacherId) {
        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new IllegalArgumentException("Subject not found"));

        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new IllegalArgumentException("Teacher not found"));

        CourseAssignment assignment = new CourseAssignment();
        assignment.setSubject(subject);
        assignment.setTeacher(teacher);

        courseAssignmentRepository.save(assignment);
    }
}
