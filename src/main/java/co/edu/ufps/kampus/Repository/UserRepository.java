package co.edu.ufps.kampus.Repository;
import co.edu.ufps.kampus.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {}

