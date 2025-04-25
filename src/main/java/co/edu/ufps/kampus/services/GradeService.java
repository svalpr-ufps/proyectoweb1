package co.edu.ufps.kampus.services;

import co.edu.ufps.kampus.dtos.request.GradeRequestDTO;
import co.edu.ufps.kampus.entities.Grade;
import co.edu.ufps.kampus.repositories.GradeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class GradeService {

    private final GradeRepository gradeRepository;

    public GradeService(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }

    public List<GradeRequestDTO> getGradesByStudentId(UUID studentId) {
        return gradeRepository.findByAcademicRecord_Student_Id(studentId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private GradeRequestDTO convertToDTO(Grade grade) {
        return GradeRequestDTO.builder()
                .courseCode(grade.getCourse().getCode())
                .period(grade.getPeriod())
                .value(grade.getValue())
                .build();
    }
}