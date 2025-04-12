package co.edu.ufps.kampus.dtos.request;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MessageRequestDTO {
    @NotNull(message = "El ID del destinatario es obligatorio")
    private UUID receiverId;
    
    @NotBlank(message = "El contenido no puede estar vacío")
    @Size(max = 1000, message = "El mensaje no puede exceder 1000 caracteres")
    private String content;
}