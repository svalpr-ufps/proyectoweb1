package co.edu.ufps.kampus.services;

import co.edu.ufps.kampus.dtos.request.MessageRequestDTO;
import co.edu.ufps.kampus.dtos.response.MessageResponseDTO;
import java.util.List;
import java.util.UUID;

public interface MessageService {
    MessageResponseDTO sendMessage(UUID senderId, MessageRequestDTO dto);
    List<MessageResponseDTO> getReceivedMessages(UUID userId);
    void markAsRead(UUID messageId);
}