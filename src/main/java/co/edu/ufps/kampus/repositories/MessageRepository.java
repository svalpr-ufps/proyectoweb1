package co.edu.ufps.kampus.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.ufps.kampus.entities.Message;

public interface MessageRepository extends JpaRepository<Message, UUID> {
    // Mensajes recibidos por un usuario
    List<Message> findByReceiverIdOrderBySentAtDesc(UUID userId);
    
    // Mensajes enviados por un usuario
    List<Message> findBySenderIdOrderBySentAtDesc(UUID userId);
    
    // Mensajes no leídos
    List<Message> findByReceiverIdAndIsReadFalse(UUID userId);
}