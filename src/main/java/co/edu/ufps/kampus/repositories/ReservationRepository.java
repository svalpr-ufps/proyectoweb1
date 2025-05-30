package co.edu.ufps.kampus.repositories;

import co.edu.ufps.kampus.entities.Reservation;
import co.edu.ufps.kampus.entities.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, UUID> {
    List<Reservation> findByUserId(UUID userId);
    List<Reservation> findByResourceId(UUID resourceId);
    List<Reservation> findByStartDateBetween(LocalDateTime start, LocalDateTime end);
    boolean existsByResourceIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
            UUID resourceId, LocalDateTime end, LocalDateTime start);
    boolean existsByRoomIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(UUID roomId, LocalDateTime end,
            LocalDateTime start);
    Optional<User> findByIdAndUserId(UUID reservationId, UUID userId);
}