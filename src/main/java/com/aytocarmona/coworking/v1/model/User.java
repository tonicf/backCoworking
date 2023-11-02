package com.aytocarmona.coworking.v1.model;

import jakarta.persistence.*;
import lombok.Data;


import java.util.List;

/**
 * Class representing a user in the coworking system.
 */
@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String surnames;

    private boolean authorized;

    private String dni;

    private String email;

    private String numberPhone;

    private String password;

    @ManyToMany
    private List<Classroom> associatedClassrooms;

    /**
     * Default constructor for User.
     */
    public User() {
    }

    /**
     * Constructor for User with parameters.
     *
     * @param id                  The user's ID.
     * @param name                The user's name.
     * @param surnames            The user's surnames.
     * @param dni                 The user's identification document (DNI).
     * @param email               The user's email address.
     * @param numberPhone         The user's phone number.
     * @param password            The user's password.
     * @param associatedClassrooms The classrooms associated with the user.
     * @param authorized           A boolean indicating user authorization.
     */
    public User(Long id, String name, String surnames, String dni, String email, String numberPhone, String password, List<Classroom> associatedClassrooms, boolean authorized) {
        this.id = id;
        this.name = name;
        this.surnames = surnames;
        this.dni = dni;
        this.email = email;
        this.numberPhone = numberPhone;
        this.password = password;
        this.associatedClassrooms = associatedClassrooms;
        this.authorized = authorized;
    }
}


