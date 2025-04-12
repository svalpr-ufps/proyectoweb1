package co.edu.ufps.kampus.dtos.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class TeacherResponseDTO {
    private UUID id;
    private String teacherCode;
    private String firstName;
    private String lastName;
    private String email;
    private String specialization;
    private LocalDateTime createdAt;
    private List<SubjectSimpleDTO> subjects;

    @Data
    @Builder
    public static class SubjectSimpleDTO {
        private UUID id;
        private String code;
        private String name;
    }
}