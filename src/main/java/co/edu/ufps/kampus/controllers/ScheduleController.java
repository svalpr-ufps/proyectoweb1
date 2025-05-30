package co.edu.ufps.kampus.controllers;

import co.edu.ufps.kampus.entities.Schedule;
import co.edu.ufps.kampus.services.ScheduleService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.UUID;

@RestController
@RequestMapping("/api/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<Schedule> createSchedule(
            @RequestBody ScheduleRequest request) {
        
        Schedule schedule = scheduleService.createSchedule(
            request.getCourseId(),
            request.getRoomId(),
            request.getDay(),
            request.getStartTime(),
            request.getEndTime()
        );
        
        return ResponseEntity.status(HttpStatus.CREATED).body(schedule);
    }

    // DTO para la solicitud
    @Getter
    @Setter
    public static class ScheduleRequest {
        private UUID courseId;
        private UUID roomId;
        private DayOfWeek day;
        private LocalTime startTime;
        private LocalTime endTime;
    }
}