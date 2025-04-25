package co.edu.ufps.kampus.request;

import co.edu.ufps.kampus.entities.DayOfWeek;
import jakarta.validation.constraints.NotNull;
import java.time.LocalTime;
import java.util.UUID;

public record ScheduleRequestDTO(
    @NotNull(message = "Subject ID is required")
    UUID subjectId,
    
    @NotNull(message = "Classroom ID is required")
    UUID classroomId,
    
    @NotNull(message = "Day of week is required")
    DayOfWeek day,
    
    @NotNull(message = "Start time is required")
    LocalTime startTime,
    
    @NotNull(message = "End time is required")
    LocalTime endTime
) {}