package co.edu.ufps.kampus.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ufps.kampus.dtos.request.GradeRequestDTO;
import co.edu.ufps.kampus.dtos.response.GradeResponseDTO;
import co.edu.ufps.kampus.entities.AcademicRecord;
import co.edu.ufps.kampus.entities.Evaluation;
import co.edu.ufps.kampus.entities.Grade;
import co.edu.ufps.kampus.entities.Student;
import co.edu.ufps.kampus.entities.Subject;
import co.edu.ufps.kampus.repositories.AcademicRecordRepository;
import co.edu.ufps.kampus.repositories.EvaluationRepository;
import co.edu.ufps.kampus.repositories.GradeRepository;
import co.edu.ufps.kampus.repositories.StudentRepository;
import co.edu.ufps.kampus.repositories.SubjectRepository;;

@Service
public class GradeService {

    @Autowired
    private GradeRepository gradeRepository;
    @Autowired
    private EvaluationRepository evaluationRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private AcademicRecordRepository academicRecordRepository;
    @Autowired
    private SubjectRepository subjectRepository;


    public List<GradeResponseDTO> getGradesByStudentId(UUID studentId) {
        List<Grade> grades = gradeRepository.findByAcademicRecord_Student_Id(studentId);

        return grades.stream().map(grade -> GradeResponseDTO.builder()
                .id(grade.getId())
                .studentCode(grade.getAcademicRecord().getStudent().getStudentCode())
                .subjectCode(grade.getEvaluation().getSubject().getCode())
                .subjectName(grade.getEvaluation().getSubject().getName())
                .period(grade.getPeriod())
                .value(grade.getScore())
                .status(grade.getScore() >= 3.0 ? "APROBADO" : "REPROBADO")
                .build()
        ).toList();
    }


    public GradeResponseDTO registerGrade(GradeRequestDTO request) {
        // Validaciones
        if (request.getValue() == null || request.getValue() < 0.0 || request.getValue() > 5.0) {
            throw new IllegalArgumentException("La nota debe estar entre 0.0 y 5.0");
        }

        // Obtener Student
        Student student = studentRepository.findByStudentCode(request.getStudentCode())
            .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));

        // Obtener AcademicRecord
        AcademicRecord record = academicRecordRepository.findByStudentId(student.getId())
            .orElseThrow(() -> new RuntimeException("Registro académico no encontrado"));

        // Obtener Subject
        Subject subject = subjectRepository.findByCode(request.getSubjectCode())
            .orElseThrow(() -> new RuntimeException("Materia no encontrada"));

        // Obtener Evaluation (asumiendo que existe una evaluación por periodo)
        Evaluation evaluation = evaluationRepository.findBySubjectId(subject.getId()).stream()
            .filter(e -> e.getPeriod().equals(request.getPeriod()))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("No existe evaluación para este periodo"));

        // Crear Grade
        Grade grade = new Grade();
        grade.setScore(request.getValue());
        grade.setPeriod(request.getPeriod()); // Asegúrate de que el campo exista
        grade.setComments(request.getComments()); // Campo opcional
        grade.setAcademicRecord(record);
        grade.setEvaluation(evaluation);

        Grade savedGrade = gradeRepository.save(grade);

        return GradeResponseDTO.builder()
        .id(savedGrade.getId())
        .studentCode(student.getStudentCode())
        .subjectCode(subject.getCode())
        .subjectName(subject.getName())
        .period(savedGrade.getPeriod())
        .value(savedGrade.getScore())
        .status(savedGrade.getScore() >= 3.0 ? "APROBADO" : "REPROBADO")
        .build();
    }
}
