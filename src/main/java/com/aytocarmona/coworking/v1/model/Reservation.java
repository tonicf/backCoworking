package com.aytocarmona.coworking.v1.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity(name = "reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "classroom_id")
    private Classroom classroom;

    private LocalDate date;

    /**
     * Default constructor for Reservation.
     */
    public Reservation() {
    }

    /**
     * Constructor for Reservation with parameters.
     *
     * @param user      The user making the reservation.
     * @param classroom The classroom being reserved.
     * @param date      The date of the reservation.
     */
    public Reservation(User user, Classroom classroom, LocalDate date) {
        this.user = user;
        this.classroom = classroom;
        this.date = date;
    }
}

