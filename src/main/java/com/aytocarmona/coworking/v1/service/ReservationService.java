package com.aytocarmona.coworking.v1.service;

import com.aytocarmona.coworking.v1.dto.ClassroomAvailabilityDto;
import com.aytocarmona.coworking.v1.model.Reservation;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Service interface for managing Reservation entities.
 * Defines methods for performing operations related to reservations.
 */
@Service
public interface ReservationService {
    /**
     * Retrieves a list of all reservations.
     *
     * @return List of Reservation objects
     */
    List<Reservation> findAll();

    /**
     * Retrieves a reservation by its ID.
     *
     * @param id ID of the reservation to look for
     * @return Optional containing the reservation if found, or empty if not
     */
    Optional<Reservation> findById(Long id);

    /**
     * Deletes a reservation by its ID.
     *
     * @param id ID of the reservation to delete
     */
    void deleteById(Long id);

    /**
     * Creates a new reservation.
     *
     * @param userId       User ID
     * @param classroomId  Classroom ID
     * @param startDateStr Start date as a string
     * @param endDateStr   End date as a string
     * @return True if the reservation is successful, false if it cannot be reserved
     */
    boolean booking(Long userId, Long classroomId, String startDateStr, String endDateStr);

    /**
     * Retrieves a list of classroom availabilities for a specific classroom.
     *
     * @param classroomId Classroom ID
     * @return List of ClassroomAvailabilityDto objects representing availabilities
     */
    List<ClassroomAvailabilityDto> classroomAvailabilitiesList(Long classroomId);

    /**
     * Checks if a classroom has a reservation on a specific date.
     *
     * @param classroomId Classroom ID
     * @param date       Date to check for reservations
     * @return True if the classroom has a reservation on the given date, false otherwise
     */
    boolean hasReservation(Long classroomId, LocalDate date);
}

