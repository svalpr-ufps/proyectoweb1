package co.edu.ufps.kampus.services;

import co.edu.ufps.kampus.entities.*;
import java.time.LocalTime;

public interface ScheduleValidatorService {
    void validateSchedule(Subject subject, Classroom classroom, 
                        DayOfWeek day, LocalTime startTime, 
                        LocalTime endTime, UUID excludeId);
    
    boolean isTimeSlotValid(LocalTime startTime, LocalTime endTime);
    boolean isClassroomAvailable(Classroom classroom, DayOfWeek day, 
                               LocalTime startTime, LocalTime endTime, 
                               UUID excludeId);
    boolean isTeacherAvailable(Teacher teacher, DayOfWeek day, 
                             LocalTime startTime, LocalTime endTime, 
                             UUID excludeId);
}