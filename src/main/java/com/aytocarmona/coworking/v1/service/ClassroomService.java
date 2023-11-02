package com.aytocarmona.coworking.v1.service;

import com.aytocarmona.coworking.v1.dto.ClassroomDto;
import com.aytocarmona.coworking.v1.model.Classroom;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service interface for managing Classroom entities.
 * Defines methods for performing operations related to Classroom.
 */
@Service
public interface ClassroomService {

    /**
     * Saves a new classroom in the database.
     *
     * @param classroom ClassroomDto object to be saved
     */
    public void save(ClassroomDto classroom);

    /**
     * Retrieves a list of all classrooms.
     *
     * @return List of Classroom objects
     */
    public List<Classroom> findAll();

    /**
     * Retrieves a classroom by its ID.
     *
     * @param id ID of the classroom to look for
     * @return Optional containing the classroom if found, or empty if not
     */
    public Optional<Classroom> findById(Long id);

    /**
     * Deletes a classroom by its ID.
     *
     * @param id ID of the classroom to delete
     */
    public void deleteById(Long id);
}


