package co.edu.ufps.kampus.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ufps.kampus.dtos.request.GradeRequestDTO;
import co.edu.ufps.kampus.dtos.response.GradeResponseDTO;
import co.edu.ufps.kampus.entities.Grade;
import co.edu.ufps.kampus.repositories.GradeRepository;;

@Service
public class GradeService {

    @Autowired
    private GradeRepository gradeRepository;

    public List<GradeRequestDTO> getGradesByStudentId(UUID studentId) {
        List<Grade> grades = gradeRepository.findByAcademicRecord_Student_Id(studentId);
        return grades.stream().map(grade -> {
            GradeRequestDTO dto = new GradeRequestDTO();
            dto.setCourseCode(grade.getCourse().getCode());
            dto.setCourseName(grade.getCourse().getName());
            dto.setPeriod(grade.getPeriod());
            dto.setValue(grade.getValue());
            return dto;
        }).toList();
    }
}
