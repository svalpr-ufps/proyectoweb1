package co.edu.ufps.kampus.controllers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.ufps.kampus.dtos.request.ReservationRequestDTO;
import co.edu.ufps.kampus.dtos.response.ReservationResponseDTO;
import co.edu.ufps.kampus.entities.Room;
import co.edu.ufps.kampus.entities.RoomType;
import co.edu.ufps.kampus.services.ReservationService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping
    public ResponseEntity<ReservationResponseDTO> createReservation(
        @Valid @RequestBody ReservationRequestDTO request,
        @RequestHeader("X-User-Id") UUID userId // Asume que el ID viene en headers
    ) {
        return ResponseEntity.ok(reservationService.createReservation(request, userId));
    }

    @GetMapping("/available-rooms")
    public ResponseEntity<Object> getAvailableRooms(
        @RequestParam LocalDateTime startDate,
        @RequestParam LocalDateTime endDate,
        @RequestParam(required = false) RoomType type
    ) {
        // Lógica para listar aulas disponibles (implementar en servicio)
        return ResponseEntity.ok(reservationService.findAvailableRooms(startDate, endDate, type));
    }
}