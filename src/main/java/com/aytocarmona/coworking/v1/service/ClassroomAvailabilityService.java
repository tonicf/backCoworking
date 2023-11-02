package com.aytocarmona.coworking.v1.service;

import com.aytocarmona.coworking.v1.model.ClassroomAvailability;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Service interface for managing Classroom Availability entities.
 */
@Service
public interface ClassroomAvailabilityService {
 /**
  * Saves a new Classroom Availability.
  *
  * @param classroomAvailability Classroom Availability object to be saved
  */
 void save(ClassroomAvailability classroomAvailability);

 /**
  * Retrieves a list of all Classroom Availability records.
  *
  * @return List of Classroom Availability objects
  */
 List<ClassroomAvailability> findAll();

 /**
  * Retrieves a Classroom Availability by its ID.
  *
  * @param id Classroom Availability ID
  * @return Optional containing the Classroom Availability, or empty if not found
  */
 Optional<ClassroomAvailability> findById(Long id);

 /**
  * Deletes a Classroom Availability by its ID.
  *
  * @param id Classroom Availability ID
  */
 void deleteById(Long id);
}

