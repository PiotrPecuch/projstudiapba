package com.projpba.projpba.Controller;

import com.projpba.projpba.DTO.ReservationDto;
import com.projpba.projpba.Entity.Reservation;
import com.projpba.projpba.Entity.Room;
import com.projpba.projpba.Entity.User;
import com.projpba.projpba.Mapper.ReservationDtoToReservationMapper;
import com.projpba.projpba.Repository.ReservationRepository;
import com.projpba.projpba.Repository.RoomRepository;
import com.projpba.projpba.Repository.UserRepository;
import com.projpba.projpba.Services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("room/reservation")
@CrossOrigin(origins = "http://localhost:3000")
@Async
public class RoomReservationController {

    @Autowired
    RoomService roomService;
    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ReservationDtoToReservationMapper reservationMapper;

    @GetMapping("/available")
    @PreAuthorize("hasRole('USER')")
    public List<Room> getAvailableRooms(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate,
            @RequestParam Long hotelId) {
        System.out.println("dostepnosc");
        System.out.println(startDate);
        System.out.println(endDate);
        return roomService.findAvailableRooms(startDate, endDate, hotelId);
    }

    @PostMapping("/confirmation")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> confirmReservation(@RequestBody ReservationDto reservationDto) {
        System.out.println("potwierdzenie");
        System.out.println(reservationDto.getRoomId());
        System.out.println(reservationDto.getStartDate());
        Reservation reservation = reservationMapper.reservationDtoToReservation(reservationDto);
        try {
            reservationRepository.save(reservation);
            return ResponseEntity.ok("Rezerwacja została potwierdzona");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\": \"Błąd podczas potwierdzania rezerwacji\"}");

        }
    }

    @GetMapping("/get/reservatedRooms")
    @PreAuthorize("hasRole('USER')")
    public List<Reservation> getReservationsByUserEmail(@RequestParam  String email) {
        Optional<User> user = userRepository.findByUserEmail(email);
        if (user != null) {
            return reservationRepository.findByUser(user.get());
        } else {
            return List.of();
        }
    }

    @DeleteMapping("/deleteReservation")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> deleteReservation(@RequestParam Long reservationId) {
        try {
            Optional<Reservation> existingReservation = reservationRepository.findById(reservationId);

            if (existingReservation.isPresent()) {
                reservationRepository.deleteById(reservationId);
                return new ResponseEntity<>("Reservation deleted successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Reservation not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error deleting reservation", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
