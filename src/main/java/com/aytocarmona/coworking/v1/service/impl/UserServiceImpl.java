package com.aytocarmona.coworking.v1.service.impl;

import com.aytocarmona.coworking.v1.dto.UserDto;
import com.aytocarmona.coworking.v1.model.User;
import com.aytocarmona.coworking.v1.repository.UserRepository;
import com.aytocarmona.coworking.v1.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Implementation of the service for the User entity.
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Saves a new user.
     *
     * @param userDto User data transfer object
     */
    @Override
    public void save(UserDto userDto) {
        User user = new User();
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode(userDto.getPassword());
        String dni = userDto.getDni();

        if (dni == "" || dni.isEmpty() || dni == null) {
            throw new IllegalArgumentException("DNI cannot be empty.");
        } else {
            Optional<User> userSearch = userRepository.findOneByDni(dni);
            if (userSearch.isPresent()) {
                throw new IllegalArgumentException("User with DNI " + dni + " already exists.");
            } else {
                user.setName(userDto.getName());
                user.setSurnames(userDto.getSurname());
                user.setEmail(userDto.getEmail());
                user.setDni(userDto.getDni());
                user.setPassword(password);
                user.setNumberPhone(userDto.getPhone());
                user.setAuthorized(false);
                userRepository.save(user);
            }
        }
    }

    /**
     * Retrieves a list of all users.
     *
     * @return List of users
     */
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    /**
     * Retrieves a user by their ID.
     *
     * @param id User ID
     * @return Optional containing the user, or empty if not found
     */
    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    /**
     * Deletes a user by their ID.
     *
     * @param id User ID
     */
    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    /**
     * Updates an existing user's information.
     *
     * @param id   User ID to update
     * @param user User object with updated information
     */
    @Override
    public void updateUser(Long id, User user) {
        Optional<User> userSearch = userRepository.findById(id);
        if (userSearch.isPresent()) {
            User newUser = userSearch.get();
            newUser.setName(user.getName());
            newUser.setSurnames(user.getSurnames());
            newUser.setDni(user.getDni());
            newUser.setEmail(user.getEmail());
            newUser.setNumberPhone(user.getNumberPhone());
            newUser.setPassword(user.getPassword());
            userRepository.save(newUser);
        } else {
            throw new NoSuchElementException("User not found with ID: " + id);
        }
    }
}
