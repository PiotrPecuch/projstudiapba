package com.projpba.projpba.Services;


import com.projpba.projpba.Entity.Reservation;
import com.projpba.projpba.Entity.Room;
import com.projpba.projpba.Repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomService {
    @Autowired
    RoomRepository roomRepository;


    public List<Room> findAvailableRooms(LocalDate startDate, LocalDate endDate, Long hotelId) {
        List<Room> allRooms = roomRepository.findAllByHotelId(Math.toIntExact(hotelId));
        return allRooms.stream().filter(room -> isAvailableInDateRange(room.getRoomID(), startDate, endDate, hotelId))
                .collect(Collectors.toList());
    }


    public Boolean isAvailableInDateRange(Long roomId, LocalDate startDate, LocalDate endDate, Long hotelId) {
        List<Room> reservations = roomRepository.getReservationsByRoomIdAndDateRange(roomId, startDate, endDate, hotelId);
        return reservations.isEmpty();
    }


}
