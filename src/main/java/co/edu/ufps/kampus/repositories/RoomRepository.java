package co.edu.ufps.kampus.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.ufps.kampus.entities.Room;
import co.edu.ufps.kampus.entities.RoomStatus;
import co.edu.ufps.kampus.entities.RoomType;

@Repository
public interface RoomRepository extends JpaRepository<Room, UUID> {
    List<Room> findByTypeAndStatus(RoomType type, RoomStatus status);
    List<Room> findByCapacityGreaterThanEqualAndStatus(Integer capacity, RoomStatus status);
    List<Room> findByHasProjectorAndStatus(boolean hasProjector, RoomStatus status);
}
