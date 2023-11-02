package com.aytocarmona.coworking.v1.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity(name = "classroom_availability")
public class ClassroomAvailability {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "classroom_id")
    private Classroom classroom;

    private LocalDate date;

    /**
     * Default constructor for ClassroomAvailability.
     */
    public ClassroomAvailability() {
    }

    /**
     * Constructor for ClassroomAvailability with parameters.
     *
     * @param id        The ID of the availability.
     * @param classroom The associated classroom.
     */
    public ClassroomAvailability(Long id, Classroom classroom) {
        this.id = id;
        this.classroom = classroom;
    }
}

