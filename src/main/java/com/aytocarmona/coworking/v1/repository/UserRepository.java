package com.aytocarmona.coworking.v1.repository;

import com.aytocarmona.coworking.v1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository interface for the User entity.
 * Provides methods for performing CRUD operations on the User entity.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Find a user by their DNI.
     *
     * @param dni The DNI of the user to be found.
     * @return An Optional containing the user if found, or empty if not.
     */
    Optional<User> findOneByDni(String dni);
}

