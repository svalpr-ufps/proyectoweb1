package co.edu.ufps.kampus.dtos.response;

import java.time.LocalDateTime;
import java.util.UUID;

import co.edu.ufps.kampus.entities.RoomType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationResponseDTO {
    private UUID id;
    private String roomCode;
    private RoomType roomType;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String purpose;
    private String status;
}