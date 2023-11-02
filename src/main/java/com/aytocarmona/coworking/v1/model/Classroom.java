package com.aytocarmona.coworking.v1.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

/**
 * Clase que representa una aula en el sistema de coworking.
 */
@Data
@Entity
@Table(name = "classrooms")
public class Classroom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany
    private List<User> users;

    @ManyToMany(mappedBy = "classroom")
    private List<ClassroomAvailability> classroomAvailabilities;

    /**
     * Default constructor for Classroom.
     */
    public Classroom() {
    }

    /**
     * Constructor for Classroom with parameters.
     *
     * @param id                    The ID of the classroom.
     * @param name                  The name of the classroom.
     * @param classroomAvailabilities List of classroom availabilities.
     * @param users                 List of users associated with the classroom.
     */
    public Classroom(Long id, String name, List<ClassroomAvailability> classroomAvailabilities, List<User> users) {
        this.id = id;
        this.name = name;
        this.classroomAvailabilities = classroomAvailabilities;
        this.users = users;
    }
}


