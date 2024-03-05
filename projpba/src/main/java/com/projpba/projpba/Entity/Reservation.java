package com.projpba.projpba.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reservations")
@Builder
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ReservationID")
    private Long reservationID;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "roomID")
    private Room room;

    @Column(nullable = false)
    private LocalDate checkInDate;

    @Column(nullable = false)
    private LocalDate checkOutDate;

    @Column(nullable = false)
    private double totalPrice;

    @Column(name = "ReservationStatus")
    private String reservationStatus;
}
