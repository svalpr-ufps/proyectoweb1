package co.edu.ufps.kampus.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.ufps.kampus.dtos.request.MessageRequestDTO;
import co.edu.ufps.kampus.dtos.response.MessageResponseDTO;
import co.edu.ufps.kampus.entities.User;
import co.edu.ufps.kampus.services.MessageService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/messages")
@RequiredArgsConstructor
public class MessageController {
    private final MessageService messageService;

     @PostMapping
    public ResponseEntity<MessageResponseDTO> sendMessage(
            @AuthenticationPrincipal User sender,
            @Valid @RequestBody MessageRequestDTO dto) {
        return ResponseEntity.ok(messageService.sendMessage(sender.getId(), dto));
    }

    @GetMapping("/received")
    public List<MessageResponseDTO> getReceivedMessages(@AuthenticationPrincipal User user) {
        return messageService.getReceivedMessages(user.getId());
    }

    @PutMapping("/{messageId}/read")
    public ResponseEntity<Void> markAsRead(@PathVariable UUID messageId) {
        messageService.markAsRead(messageId);
        return ResponseEntity.ok().build();
    }
}