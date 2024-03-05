package com.projpba.projpba.Services;

import com.projpba.projpba.Entity.Hotel;
import com.projpba.projpba.Entity.Room;
import com.projpba.projpba.Repository.HotelRepository;
import com.projpba.projpba.Repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelServices {
    @Autowired
    HotelRepository hotelRepository;

    @Autowired
    RoomRepository roomRepository;

    public List<Hotel> getAllHotelsList(){
        return (List<Hotel>) hotelRepository.findAll();
    }

    public List<Room> getAllHotelRooms(Integer hotelId){
        return roomRepository.findAllByHotelId(hotelId);
    }

}
