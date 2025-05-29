package co.edu.ufps.kampus.repositories;

import co.edu.ufps.kampus.entities.Reservation;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, UUID> {
    List<Reservation> findByUserId(UUID userId);
    List<Reservation> findByResourceId(UUID resourceId);
    List<Reservation> findByStartDateBetween(LocalDateTime start, LocalDateTime end);
    boolean existsByResourceIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
            UUID resourceId, LocalDateTime end, LocalDateTime start);

    boolean existsByRoomIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(@NotNull(message = "Room ID is required") UUID roomId, @NotNull(message = "End date/time is required") @Future(message = "End date must be in the future") LocalDateTime endDate, @NotNull(message = "Start date/time is required") @FutureOrPresent(message = "Start date must be in the present or future") LocalDateTime startDate);
}