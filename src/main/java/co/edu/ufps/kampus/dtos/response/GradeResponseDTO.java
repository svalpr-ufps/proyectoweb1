package co.edu.ufps.kampus.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Getter
@Setter
public class GradeResponseDTO {
    private UUID id;
    private String studentCode;
    private String subjectCode;
    private String subjectName;
    private String period;
    private Double value;
    private String status;
}
