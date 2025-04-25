package co.edu.ufps.kampus.services.impl;

import co.edu.ufps.kampus.entities.*;
import co.edu.ufps.kampus.exceptions.ConflictException;
import co.edu.ufps.kampus.repositories.ScheduleRepository;
import co.edu.ufps.kampus.services.ScheduleValidatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ScheduleValidatorServiceImpl implements ScheduleValidatorService {
    
    private final ScheduleRepository scheduleRepository;

    @Override
    public void validateSchedule(Subject subject, Classroom classroom, 
                               DayOfWeek day, LocalTime startTime, 
                               LocalTime endTime, UUID excludeId) {
        if (!isTimeSlotValid(startTime, endTime)) {
            throw new IllegalArgumentException("Invalid time slot");
        }
        
        if (!isClassroomAvailable(classroom, day, startTime, endTime, excludeId)) {
            throw new ConflictException("Classroom not available");
        }
        
        if (subject.getTeacher() != null && 
            !isTeacherAvailable(subject.getTeacher(), day, startTime, endTime, excludeId)) {
            throw new ConflictException("Teacher not available");
        }
    }

    @Override
    public boolean isTimeSlotValid(LocalTime startTime, LocalTime endTime) {
        return startTime.isBefore(endTime);
    }

    @Override
    public boolean isClassroomAvailable(Classroom classroom, DayOfWeek day, 
                                      LocalTime startTime, LocalTime endTime, 
                                      UUID excludeId) {
        return !scheduleRepository.existsClassroomConflict(
            classroom.getId(), day, startTime, endTime, excludeId);
    }

    @Override
    public boolean isTeacherAvailable(Teacher teacher, DayOfWeek day, 
                                    LocalTime startTime, LocalTime endTime, 
                                    UUID excludeId) {
        return !scheduleRepository.existsTeacherConflict(
            teacher.getId(), day, startTime, endTime, excludeId);
    }
}