package co.edu.ufps.kampus.repositories;

import co.edu.ufps.kampus.entities.Teacher;
import org.apache.el.stream.Stream;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, UUID> {
    boolean existsByEmail(String email);
    Optional<Teacher> findByTeacherCode(String code);
    Optional<Teacher> findTopByOrderByTeacherCodeDesc();

    List<Teacher> findBySpecialization(String specialization);

    Stream findByEmail(String email);
}