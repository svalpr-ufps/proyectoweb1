package co.edu.ufps.kampus.dtos.request;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationRequestDTO {
    @NotNull(message = "Room ID is required")
    private UUID roomId;

    @NotNull(message = "Start date/time is required")
    @FutureOrPresent(message = "Start date must be in the present or future")
    private LocalDateTime startDate;

    @NotNull(message = "End date/time is required")
    @Future(message = "End date must be in the future")
    private LocalDateTime endDate;

    private UUID subjectId; // Opcional

    @NotBlank(message = "Purpose is required")
    private String purpose;
}