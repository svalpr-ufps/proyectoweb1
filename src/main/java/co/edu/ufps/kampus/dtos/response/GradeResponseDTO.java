package co.edu.ufps.kampus.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GradeResponseDTO {
    private String courseCode;
    private String courseName;
    private String period;
    private Double value;
}
