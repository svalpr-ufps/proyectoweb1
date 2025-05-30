package co.edu.ufps.kampus.services;

import co.edu.ufps.kampus.entities.Room;
import co.edu.ufps.kampus.entities.Course;
import co.edu.ufps.kampus.entities.Schedule;
import co.edu.ufps.kampus.exceptions.ConflictException;
import co.edu.ufps.kampus.exceptions.EntityNotFoundException;
import co.edu.ufps.kampus.repositories.RoomRepository;
import co.edu.ufps.kampus.repositories.CourseRepository;
import co.edu.ufps.kampus.repositories.ScheduleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final CourseRepository courseRepository;
    private final RoomRepository roomRepository;

    @Transactional
    public Schedule createSchedule(UUID courseId, UUID roomId, 
                                  DayOfWeek day, LocalTime startTime, LocalTime endTime) {
        
        // Validar tiempos
        if (startTime.isAfter(endTime)) {
            throw new IllegalArgumentException("La hora de inicio debe ser antes de la hora de fin");
        }
        
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Curso no encontrado"));
        
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new EntityNotFoundException("Aula no encontrada"));
        
        // Verificar solapamientos
        List<Schedule> roomOverlaps = scheduleRepository.findOverlappingSchedules(
            room, day, startTime, endTime
        );
        
        if (!roomOverlaps.isEmpty()) {
            throw new ConflictException("El aula ya está ocupada en ese horario");
        }
        
        // Crear nuevo horario
        Schedule schedule = new Schedule(course, room, day, startTime, endTime);
        return scheduleRepository.save(schedule);
        
    }
}