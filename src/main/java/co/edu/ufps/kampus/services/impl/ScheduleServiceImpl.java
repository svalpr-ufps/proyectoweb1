package co.edu.ufps.kampus.services.impl;

import co.edu.ufps.kampus.dto.ScheduleRequest;
import co.edu.ufps.kampus.entities.*;
import co.edu.ufps.kampus.exceptions.ConflictException;
import co.edu.ufps.kampus.exceptions.ResourceNotFoundException;
import co.edu.ufps.kampus.repositories.*;
import co.edu.ufps.kampus.services.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {
    
    private final ScheduleRepository scheduleRepository;
    private final SubjectRepository subjectRepository;
    private final TeacherRepository teacherRepository;
    private final ClassroomRepository classroomRepository;

    @Override
    @Transactional
    public Schedule createSchedule(ScheduleRequest request) {
        Subject subject = subjectRepository.findById(request.subjectId())
                .orElseThrow(() -> new ResourceNotFoundException("Subject not found"));
        
        Classroom classroom = classroomRepository.findById(request.classroomId())
                .orElseThrow(() -> new ResourceNotFoundException("Classroom not found"));
        
        validateSchedule(subject, classroom, request.day(), request.startTime(), request.endTime(), null);
        
        Schedule schedule = new Schedule();
        schedule.setSubject(subject);
        schedule.setClassroom(classroom);
        schedule.setDay(request.day());
        schedule.setStartTime(request.startTime());
        schedule.setEndTime(request.endTime());
        
        return scheduleRepository.save(schedule);
    }

    @Override
    @Transactional
    public Schedule updateSchedule(UUID id, ScheduleRequest request) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Schedule not found"));
        
        Subject subject = subjectRepository.findById(request.subjectId())
                .orElseThrow(() -> new ResourceNotFoundException("Subject not found"));
        
        Classroom classroom = classroomRepository.findById(request.classroomId())
                .orElseThrow(() -> new ResourceNotFoundException("Classroom not found"));
        
        validateSchedule(subject, classroom, request.day(), request.startTime(), request.endTime(), id);
        
        schedule.setSubject(subject);
        schedule.setClassroom(classroom);
        schedule.setDay(request.day());
        schedule.setStartTime(request.startTime());
        schedule.setEndTime(request.endTime());
        
        return scheduleRepository.save(schedule);
    }

    private void validateSchedule(Subject subject, Classroom classroom, DayOfWeek day, 
                                LocalTime startTime, LocalTime endTime, UUID excludeId) {
        // Validar horas
        if (startTime.isAfter(endTime)) {
            throw new IllegalArgumentException("Start time must be before end time");
        }
        
        // Validar solapamiento de aula
        if (scheduleRepository.existsClassroomConflict(
                classroom.getId(), day, startTime, endTime, excludeId)) {
            throw new ConflictException("Classroom is already booked at this time");
        }
        
        // Validar solapamiento de profesor (si está asignado)
        if (subject.getTeacher() != null && 
            scheduleRepository.existsTeacherConflict(
                subject.getTeacher().getId(), day, startTime, endTime, excludeId)) {
            throw new ConflictException("Teacher has another class at this time");
        }
        
        // Validar capacidad del aula
        if (classroom.getCapacity() < subject.getEnrollments().size()) {
            throw new ConflictException("Classroom capacity is insufficient for enrolled students");
        }
    }

    @Override
    public List<Schedule> getSchedulesByTeacher(UUID teacherId) {
        return scheduleRepository.findByTeacherId(teacherId);
    }

    // Implementación de otros métodos de la interfaz...
}