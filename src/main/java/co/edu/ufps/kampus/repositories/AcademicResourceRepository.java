package co.edu.ufps.kampus.repositories;

import co.edu.ufps.kampus.entities.AcademicResource;
import co.edu.ufps.kampus.entities.ResourceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface AcademicResourceRepository extends JpaRepository<AcademicResource, UUID> {
    List<AcademicResource> findByType(ResourceType type);
    List<AcademicResource> findByAvailable(boolean available);
}