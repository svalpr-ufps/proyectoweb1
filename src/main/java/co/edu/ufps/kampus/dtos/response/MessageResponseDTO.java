package co.edu.ufps.kampus.dtos.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter @Setter
@Builder
public class MessageResponseDTO {
    private UUID id;
    private String senderName;
    private String receiverName;
    private String content;
    private LocalDateTime sentAt;
    private boolean isRead;
}