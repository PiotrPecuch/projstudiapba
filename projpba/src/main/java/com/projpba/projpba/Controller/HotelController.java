package com.projpba.projpba.Controller;

import com.projpba.projpba.Entity.Hotel;
import com.projpba.projpba.Entity.Room;
import com.projpba.projpba.Repository.HotelRepository;
import com.projpba.projpba.Services.HotelServices;
import com.projpba.projpba.Services.JwtDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/hotel")
@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class HotelController {

    @Autowired
    HotelServices hotelServices;


    @GetMapping("/list")
    @PreAuthorize("hasRole('USER')")
    public List<Hotel> getAllHotels() {
        return hotelServices.getAllHotelsList();
    }

    @GetMapping("/list/room")
    @PreAuthorize("hasRole('USER')")
    public List<Room> getAllRooms(@RequestParam Integer hotelId){
        return hotelServices.getAllHotelRooms(hotelId);
    }



}
