package co.edu.ufps.kampus.mappers;

import co.edu.ufps.kampus.dtos.request.SubjectRequestDTO;
import co.edu.ufps.kampus.dtos.response.SubjectResponseDTO;
import co.edu.ufps.kampus.entities.*;
import org.springframework.stereotype.Component;

@Component
public class SubjectMapper {

    public Subject toEntity(SubjectRequestDTO dto) {
        Subject subject = new Subject();
        subject.setCode(dto.getCode());
        subject.setName(dto.getName());
        subject.setSchedule(dto.getSchedule());
        subject.setClassroom(dto.getClassroom());
        subject.setCapacity(dto.getCapacity());
        // Note: Course se establece en el servicio
        return subject;
    }

    public SubjectResponseDTO toDTO(Subject entity) {
        return SubjectResponseDTO.builder()
                .id(entity.getId())
                .code(entity.getCode())
                .name(entity.getName())
                .schedule(entity.getSchedule())
                .classroom(entity.getClassroom())
                .capacity(entity.getCapacity())
                .course(mapCourse(entity.getCourse()))
                .teacher(mapTeacher(entity.getTeacher()))
                .build();
    }

    public void updateEntityFromDTO(SubjectRequestDTO dto, Subject entity) {
        if (dto.getCode() != null) entity.setCode(dto.getCode());
        if (dto.getName() != null) entity.setName(dto.getName());
        if (dto.getSchedule() != null) entity.setSchedule(dto.getSchedule());
        if (dto.getClassroom() != null) entity.setClassroom(dto.getClassroom());
        if (dto.getCapacity() != null) entity.setCapacity(dto.getCapacity());
    }

    private SubjectResponseDTO.CourseSimpleDTO mapCourse(Course course) {
        if (course == null) return null;
        return SubjectResponseDTO.CourseSimpleDTO.builder()
                .id(course.getId())
                .code(course.getCode())
                .name(course.getName())
                .build();
    }

    private SubjectResponseDTO.TeacherSimpleDTO mapTeacher(Teacher teacher) {
        if (teacher == null) return null;
        return SubjectResponseDTO.TeacherSimpleDTO.builder()
                .id(teacher.getId())
                .firstName(teacher.getFirstName() + " " + teacher.getLastName())
                .teacherCode(teacher.getTeacherCode())
                .build();
    }
}