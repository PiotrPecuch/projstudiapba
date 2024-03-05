package com.projpba.projpba.Mapper;

import com.projpba.projpba.DTO.ReservationDto;
import com.projpba.projpba.Entity.Reservation;
import com.projpba.projpba.Repository.ReservationRepository;
import com.projpba.projpba.Repository.RoomRepository;
import com.projpba.projpba.Repository.UserRepository;
import com.projpba.projpba.Services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.temporal.ChronoUnit;

@Component
public class ReservationDtoToReservationMapper {

    @Autowired
    RoomService roomService;
    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    UserRepository userRepository;

    public Reservation reservationDtoToReservation(ReservationDto reservationDto) {
        long daysDifference = ChronoUnit.DAYS.between(reservationDto.getStartDate(), reservationDto.getEndDate());
        Double totalPrice = roomRepository.getRoomByRoomID(reservationDto.getRoomId()).getPricePerNight() * daysDifference;

        Reservation reservation = new Reservation();
        reservation.setUser(userRepository.findByUserEmail(reservationDto.getUserEmail()).orElse(null));
        reservation.setRoom(roomRepository.getRoomByRoomID(reservationDto.getRoomId()));
        reservation.setCheckInDate(reservationDto.getStartDate());
        reservation.setCheckOutDate(reservationDto.getEndDate());
        reservation.setTotalPrice(totalPrice);
        reservation.setReservationStatus("Zarezerwowano");

        System.out.println(roomRepository.getRoomByRoomID(reservationDto.getRoomId()).getPricePerNight());
        System.out.println(reservation.getTotalPrice());

        return reservation;
    }

}

