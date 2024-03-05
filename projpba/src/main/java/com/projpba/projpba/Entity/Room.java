package com.projpba.projpba.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomID;

    @ManyToOne
    @JoinColumn(name = "hotelId")
    private Hotel hotel;

    @Column(nullable = false)
    private String roomNumber;

    @Column(nullable = false)
    private String roomType;

    @Column(nullable = false)
    private double pricePerNight;

    @Column(nullable = false)
    private boolean availability;

    @JsonBackReference
    @OneToMany(mappedBy = "room")
    private List<Reservation> reservations;
}
