package co.edu.ufps.kampus.response;

import co.edu.ufps.kampus.entities.DayOfWeek;
import java.time.LocalTime;
import java.util.UUID;

public record ScheduleResponseDTO(
    UUID id,
    UUID subjectId,
    String subjectName,
    String subjectCode,
    UUID classroomId,
    String classroomName,
    String classroomCode,
    UUID teacherId,
    String teacherName,
    DayOfWeek day,
    LocalTime startTime,
    LocalTime endTime,
    Integer enrolledStudents,
    Integer classroomCapacity
) {
    public static ScheduleResponseDTO fromEntity(Schedule schedule) {
        return new ScheduleResponseDTO(
            schedule.getId(),
            schedule.getSubject().getId(),
            schedule.getSubject().getName(),
            schedule.getSubject().getCode(),
            schedule.getClassroom().getId(),
            schedule.getClassroom().getName(),
            schedule.getClassroom().getCode(),
            schedule.getSubject().getTeacher() != null ? 
                schedule.getSubject().getTeacher().getId() : null,
            schedule.getSubject().getTeacher() != null ? 
                schedule.getSubject().getTeacher().getFullName() : null,
            schedule.getDay(),
            schedule.getStartTime(),
            schedule.getEndTime(),
            schedule.getSubject().getEnrollments().size(),
            schedule.getClassroom().getCapacity()
        );
    }
}