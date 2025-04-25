package co.edu.ufps.kampus.services.impl;

import co.edu.ufps.kampus.entities.Enrollment;
import co.edu.ufps.kampus.entities.Grade;
import co.edu.ufps.kampus.entities.Student;
import co.edu.ufps.kampus.repositories.EnrollmentRepository;
import co.edu.ufps.kampus.services.GPACalculatorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GPACalculatorServiceImpl implements GPACalculatorService {

    private final EnrollmentRepository enrollmentRepository;

    public GPACalculatorServiceImpl(EnrollmentRepository enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }

    @Override
    public double calculateSemesterGPA(Student student, String semester) {
        List<Enrollment> enrollments = enrollmentRepository.findByStudentAndSemester(student, semester);
        return calculateGPA(enrollments);
    }

    @Override
    public double calculateCumulativeGPA(Student student) {
        List<Enrollment> enrollments = enrollmentRepository.findByStudent(student);
        return calculateGPA(enrollments);
    }

    @Override
    public String getAcademicStanding(double gpa) {
        if (gpa >= 3.1) return "Good Standing";
        if (gpa >= 2.8) return "Academic Warning";
        return "Academic Probation";
    }

    private double calculateGPA(List<Enrollment> enrollments) {
        if (enrollments == null || enrollments.isEmpty()) {
            return 0.0;
        }

        double totalWeightedGrades = 0.0;
        int totalCredits = 0;

        for (Enrollment enrollment : enrollments) {
            Double grade = enrollment.getGrade().getScore();

            if (grade != null && enrollment.getSubject() != null) {
                int credits = enrollment.getSubject().getCredits();
                totalWeightedGrades += grade * credits;
                totalCredits += credits;
            }
        }

        return totalCredits > 0 ? totalWeightedGrades / totalCredits : 0.0;
    }
}
