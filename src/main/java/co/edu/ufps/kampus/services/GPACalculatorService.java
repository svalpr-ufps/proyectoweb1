package co.edu.ufps.kampus.services;

import co.edu.ufps.kampus.entities.Enrollment;
import co.edu.ufps.kampus.entities.Student;

import java.util.List;

public interface GPACalculatorService {
    double calculateSemesterGPA(Student student, String semester);
    double calculateCumulativeGPA(Student student);
    String getAcademicStanding(double gpa);
}