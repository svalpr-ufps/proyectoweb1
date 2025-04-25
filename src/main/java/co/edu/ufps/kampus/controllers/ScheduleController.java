package co.edu.ufps.kampus.controllers;

import co.edu.ufps.kampus.dto.ScheduleRequest;
import co.edu.ufps.kampus.entities.Schedule;
import co.edu.ufps.kampus.services.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/schedules")
@RequiredArgsConstructor
public class ScheduleController {
    
    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<Schedule> createSchedule(@RequestBody ScheduleRequest request) {
        return ResponseEntity.ok(scheduleService.createSchedule(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Schedule> getSchedule(@PathVariable UUID id) {
        return ResponseEntity.ok(scheduleService.getScheduleById(id));
    }

    @GetMapping("/teacher/{teacherId}")
    public ResponseEntity<List<Schedule>> getTeacherSchedules(@PathVariable UUID teacherId) {
        return ResponseEntity.ok(scheduleService.getSchedulesByTeacher(teacherId));
    }

}