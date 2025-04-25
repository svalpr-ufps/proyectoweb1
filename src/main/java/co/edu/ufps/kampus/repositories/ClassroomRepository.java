package co.edu.ufps.kampus.repositories;

import co.edu.ufps.kampus.entities.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClassroomRepository extends JpaRepository<Classroom, UUID> {
    Optional<Classroom> findByCode(String code);
    boolean existsByCode(String code);
    List<Classroom> findByCapacityGreaterThanEqual(int minCapacity);
}