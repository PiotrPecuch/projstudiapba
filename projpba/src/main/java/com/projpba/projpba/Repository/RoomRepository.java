package com.projpba.projpba.Repository;

import com.projpba.projpba.Entity.Reservation;
import com.projpba.projpba.Entity.Room;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
@EnableJpaRepositories
public interface RoomRepository extends JpaRepository<Room, Long> {


    @Query(value = "SELECT * FROM rooms WHERE hotel_id = :hotelId", nativeQuery = true)
    List<Room> findAllByHotelId(@Param("hotelId") Integer hotelId);
    Room getRoomByRoomID(Long roomId);
    @Query("SELECT r FROM Room r JOIN r.reservations res WHERE r.roomID = :roomId AND r.hotel.id = :hotelId AND (:startDate >= res.checkInDate AND :startDate <= res.checkOutDate) OR (:endDate >= res.checkInDate AND :endDate <= res.checkOutDate) OR (:startDate <= res.checkInDate AND :endDate >= res.checkOutDate)")
    List<Room> getReservationsByRoomIdAndDateRange(@Param("roomId") Long roomId, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate, @Param("hotelId") Long hotelId);

}
