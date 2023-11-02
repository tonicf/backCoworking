package com.aytocarmona.coworking.v1.service;

import com.aytocarmona.coworking.v1.dto.UserDto;
import com.aytocarmona.coworking.v1.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
/**
 * Service interface for managing User entities.
 * Defines methods for performing operations related to users.
 */
@Service
public interface UserService {
    /**
     * Saves a new user in the database.
     *
     * @param user UserDto object to be saved
     */
    void save(UserDto user);

    /**
     * Retrieves a list of all users.
     *
     * @return List of User objects
     */
    List<User> findAll();

    /**
     * Retrieves a user by their ID.
     *
     * @param id User ID to look for
     * @return Optional containing the user if found, or empty if not
     */
    Optional<User> findById(Long id);

    /**
     * Deletes a user by their ID.
     *
     * @param id User ID to delete
     */
    void deleteById(Long id);

    /**
     * Updates an existing user's information.
     *
     * @param id   User ID to update
     * @param user User object with updated information
     */
    void updateUser(Long id, User user);
}
