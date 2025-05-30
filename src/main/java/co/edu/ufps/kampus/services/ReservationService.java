package co.edu.ufps.kampus.services;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ufps.kampus.dtos.request.ReservationRequestDTO;
import co.edu.ufps.kampus.dtos.response.ReservationResponseDTO;
import co.edu.ufps.kampus.entities.Reservation;
import co.edu.ufps.kampus.entities.Room;
import co.edu.ufps.kampus.entities.RoomType;
import co.edu.ufps.kampus.entities.Subject;
import co.edu.ufps.kampus.entities.User;
import co.edu.ufps.kampus.repositories.ReservationRepository;
import co.edu.ufps.kampus.repositories.RoomRepository;
import co.edu.ufps.kampus.repositories.SubjectRepository;
import co.edu.ufps.kampus.repositories.UserRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private UserRepository userRepository; 

     @Autowired
    private SubjectRepository subjectRepository;

    public ReservationResponseDTO createReservation(ReservationRequestDTO request, UUID userId) {
        // 1. Validar horario
        if (request.getEndDate().isBefore(request.getStartDate())) {
            throw new IllegalArgumentException("End date must be after start date");
        }

        // 2. Verificar disponibilidad del aula
        boolean isRoomAvailable = !reservationRepository.existsByRoomIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
            request.getRoomId(), request.getEndDate(), request.getStartDate());

        if (!isRoomAvailable) {
            throw new IllegalStateException("Room is already booked for the selected time");
        }

        // 3. Obtener entidades relacionadas
        Room room = roomRepository.findById(request.getRoomId())
            .orElseThrow(() -> new RuntimeException("Room not found"));
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));

        // 4. Crear reserva
        Reservation reservation = new Reservation();
        reservation.setStartDate(request.getStartDate());
        reservation.setEndDate(request.getEndDate());
        reservation.setRoom(room);
        reservation.setUser(user);

        // Opcional: Asignar materia si existe
        if (request.getSubjectId() != null) {
            Subject subject = subjectRepository.findById(request.getSubjectId())
                .orElseThrow(() -> new RuntimeException("Subject not found"));
            reservation.setSubject(subject);
        }

        Reservation savedReservation = reservationRepository.save(reservation);

        // 5. Retornar DTO de respuesta
        return new ReservationResponseDTO(
            savedReservation.getId(),
            room.getCode(),
            room.getType(),
            savedReservation.getStartDate(),
            savedReservation.getEndDate(),
            savedReservation.getPurpose(),
            "CONFIRMED" // Estado por defecto
        );
    }

    public Object findAvailableRooms(LocalDateTime startDate, LocalDateTime endDate, RoomType type) {
        
        throw new UnsupportedOperationException("Unimplemented method 'findAvailableRooms'");
    }
}