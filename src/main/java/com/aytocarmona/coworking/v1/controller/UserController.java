package com.aytocarmona.coworking.v1.controller;

import com.aytocarmona.coworking.v1.dto.UserDto;
import com.aytocarmona.coworking.v1.model.User;
import com.aytocarmona.coworking.v1.service.UserService;
import jakarta.annotation.security.PermitAll;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("${api.version}/users")  // Base path for all user operations
public class UserController {

    private final UserService userService;

    /**
     * Constructor for the UserController class.
     *
     * @param userService The user service used for performing operations.
     */
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Save a new user in the database.
     *
     * @param user The User object to be saved.
     */
    @PermitAll()
    @PostMapping
    public void save(@RequestBody UserDto user) {
        userService.save(user);
    }

    /**
     * Get a list of all available users.
     *
     * @return A list of User objects.
     */
    @PreAuthorize(value = "isAuthenticated()")
    @GetMapping
    public List<User> findAll() {
        return userService.findAll();
    }

    /**
     * Get a user by their ID.
     *
     * @param id The ID of the user to be retrieved.
     * @return An Optional containing the user if found, or empty if not.
     */
    @PreAuthorize(value = "isAuthenticated()")
    @GetMapping("/{id}")
    public Optional<User> findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    /**
     * Delete a user by their ID.
     *
     * @param id The ID of the user to be deleted.
     */
    @PreAuthorize(value = "isAuthenticated()")
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        userService.deleteById(id);
    }

    /**
     * Update a user by their ID.
     *
     * @param id   The ID of the user to be updated.
     * @param user The updated User object.
     */
    @PreAuthorize(value = "isAuthenticated()")
    @PutMapping("/{id}")
    public void updateUser(@PathVariable Long id, @RequestBody User user) {
        userService.updateUser(id, user);
    }
}


