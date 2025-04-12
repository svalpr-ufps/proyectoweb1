package co.edu.ufps.kampus.repositories;

import co.edu.ufps.kampus.entities.Reservation;
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
}