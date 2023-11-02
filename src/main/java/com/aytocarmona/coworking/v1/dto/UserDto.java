package com.aytocarmona.coworking.v1.dto;

import lombok.Data;

@Data
public class UserDto {

    private Long id;
    private String dni;
    private String password;
    private boolean authorized;
    private String name;
    private String surname;
    private String phone;
    private String email;

    /**
     * Constructor for creating a token.
     *
     * @param id       The user's ID.
     * @param dni      The user's DNI.
     * @param password The user's password.
     */
    public UserDto(Long id, String dni, String password) {
        this.id = id;
        this.dni = dni;
        this.password = password;
    }

    /**
     * Constructor for creating a new user.
     *
     * @param dni        The user's DNI.
     * @param password   The user's password.
     * @param name       The user's name.
     * @param surname    The user's surname.
     * @param phone      The user's phone number.
     * @param email      The user's email.
     * @param authorized A boolean indicating user authorization.
     */
    public UserDto(String dni, String password, String name, String surname, String phone, String email, boolean authorized) {
        this.dni = dni;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.email = email;
        this.authorized = authorized;
    }

    public UserDto() {
    }
}
