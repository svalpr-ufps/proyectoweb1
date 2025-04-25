package co.edu.ufps.kampus.services.impl;

import java.util.UUID;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ufps.kampus.entities.Teacher;
import co.edu.ufps.kampus.repositories.SubjectRepository;
import co.edu.ufps.kampus.repositories.TeacherRepository;

@Service
public class CourseAssignmentServiceImpl {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    public void assignSubjectToTeacher(UUID subjectId, UUID teacherId) {
        co.edu.ufps.kampus.entities.Subject subject = subjectRepository.findById(subjectId)
            .orElseThrow(() -> new RuntimeException("Subject not found"));
        Teacher teacher = teacherRepository.findById(teacherId)
            .orElseThrow(() -> new RuntimeException("Teacher not found"));

        teacher.assignSubject(subject);

        teacherRepository.save(teacher); // cascades to subject
    }
}