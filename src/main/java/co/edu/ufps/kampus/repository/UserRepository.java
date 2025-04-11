package co.edu.ufps.kampus.repository;
import co.edu.ufps.kampus.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {}

