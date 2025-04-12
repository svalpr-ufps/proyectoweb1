package co.edu.ufps.kampus.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubjectResponseDTO {
    private UUID id;
    private String code;
    private String name;
    private String schedule;
    private String classroom;
    private Integer capacity;
    private CourseSimpleDTO course;
    private TeacherSimpleDTO teacher;
    private List<AcademicResourceSimpleDTO> resources;

    public SubjectResponseDTO(UUID id, String name, String code) {
        this.id = id;
        this.name = name;
        this.code = code;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CourseSimpleDTO {
        private UUID id;
        private String code;
        private String name;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TeacherSimpleDTO {
        private UUID id;
        private String firstName;
        private String lastName;
        private String teacherCode;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AcademicResourceSimpleDTO {
        private UUID id;
        private String name;
        private String type;
    }
}