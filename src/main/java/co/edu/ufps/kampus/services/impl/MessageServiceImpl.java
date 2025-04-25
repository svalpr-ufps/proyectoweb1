package co.edu.ufps.kampus.services.impl;

import co.edu.ufps.kampus.dtos.request.MessageRequestDTO;
import co.edu.ufps.kampus.dtos.response.MessageResponseDTO;
import co.edu.ufps.kampus.entities.Message;
import co.edu.ufps.kampus.entities.User;
import co.edu.ufps.kampus.repositories.MessageRepository;
import co.edu.ufps.kampus.repositories.UserRepository;
import co.edu.ufps.kampus.services.MessageService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public MessageResponseDTO sendMessage(UUID senderId, MessageRequestDTO dto) {
        User sender = userRepository.findById(senderId)
            .orElseThrow(() -> new EntityNotFoundException("Remitente no encontrado"));
        
        User receiver = userRepository.findById(dto.getReceiverId())
            .orElseThrow(() -> new EntityNotFoundException("Destinatario no encontrado"));

        Message message = new Message();
        message.setSender(sender);
        message.setReceiver(receiver);
        message.setContent(dto.getContent());
        
        message = messageRepository.save(message);
        
        return convertToDTO(message);
    }

    @Override
    public List<MessageResponseDTO> getReceivedMessages(UUID userId) {
        return messageRepository.findByReceiverIdOrderBySentAtDesc(userId)
            .stream()
            .map(this::convertToDTO)
            .toList();
    }

    @Override
    @Transactional
    public void markAsRead(UUID messageId) {
        messageRepository.findById(messageId).ifPresent(message -> {
            message.setRead(true);
            messageRepository.save(message);
        });
    }

    private MessageResponseDTO convertToDTO(Message message) {
        return MessageResponseDTO.builder()
            .id(message.getId())
            .senderName(message.getSender().getFullName())
            .receiverName(message.getReceiver().getFullName())
            .content(message.getContent())
            .sentAt(message.getSentAt())
            .isRead(message.isRead())
            .build();
    }
}