package co.edu.ufps.kampus.repositories;

import co.edu.ufps.kampus.entities.Room;
import co.edu.ufps.kampus.entities.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

public interface ScheduleRepository extends JpaRepository<Schedule, UUID> {

    // Busca horarios que coincidan en aula, día y que se solapen en tiempo
    @Query("SELECT s FROM Schedule s WHERE " +
           "s.room = :room AND " +
           "s.dayOfWeek = :day AND " +
           "((s.startTime < :endTime) AND (s.endTime > :startTime))")
    List<Schedule> findOverlappingSchedules(
        @Param("room") Room room,
        @Param("day") DayOfWeek day,
        @Param("startTime") LocalTime startTime,
        @Param("endTime") LocalTime endTime
    );
}