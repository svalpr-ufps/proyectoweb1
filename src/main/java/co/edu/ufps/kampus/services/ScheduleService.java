package co.edu.ufps.kampus.services;

import co.edu.ufps.kampus.dto.ScheduleRequest;
import co.edu.ufps.kampus.entities.Schedule;
import java.util.List;
import java.util.UUID;

public interface ScheduleService {
    Schedule createSchedule(ScheduleRequest request);
    Schedule updateSchedule(UUID id, ScheduleRequest request);
    void deleteSchedule(UUID id);
    Schedule getScheduleById(UUID id);
    List<Schedule> getAllSchedules();
    List<Schedule> getSchedulesBySubject(UUID subjectId);
    List<Schedule> getSchedulesByClassroom(UUID classroomId);
    List<Schedule> getSchedulesByTeacher(UUID teacherId);
    boolean checkTeacherAvailability(UUID teacherId, DayOfWeek day, LocalTime startTime, LocalTime endTime);
    boolean checkClassroomAvailability(UUID classroomId, DayOfWeek day, LocalTime startTime, LocalTime endTime);
}