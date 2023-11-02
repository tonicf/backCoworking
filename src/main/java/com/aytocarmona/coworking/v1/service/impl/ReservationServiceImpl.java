package com.aytocarmona.coworking.v1.service.impl;

import com.aytocarmona.coworking.v1.dto.BookingDto;
import com.aytocarmona.coworking.v1.dto.ClassroomAvailabilityDto;
import com.aytocarmona.coworking.v1.model.*;
import com.aytocarmona.coworking.v1.repository.ReservationRepository;
import com.aytocarmona.coworking.v1.service.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final ClassroomService classroomService;
    private final UserService userService;

    public ReservationServiceImpl(UserService userService, ReservationRepository reservationRepository, ClassroomService classroomService) {
        this.userService = userService;
        this.reservationRepository = reservationRepository;
        this.classroomService = classroomService;
    }

    @Override
    public List<Reservation> findAll() {
        // Comment: This method returns a list of all reservations. Not implemented in this code.
        return null;
    }

    @Override
    public Optional<Reservation> findById(Long id) {
        // Comment: This method retrieves a reservation by its ID. Not implemented in this code.
        return reservationRepository.findById(id);    }

    @Override
    public void deleteById(Long id) {
        // Comment: This method deletes a reservation by its ID. Not implemented in this code.
    }

    /**
     * Creates a new reservation.
     * @param userId User ID
     * @param classroomId Classroom ID
     * @param startDateStr Start date as a string
     * @param endDateStr End date as a string
     * @return True if the reservation is successful, false if it cannot be reserved.
     */
    @Transactional
    public boolean booking(Long userId, Long classroomId, String startDateStr, String endDateStr) {
        Optional<User> user = userService.findById(userId);
        Optional<Classroom> classroom = classroomService.findById(classroomId);
        LocalDate startDate = LocalDate.parse(startDateStr);
        LocalDate endDate = LocalDate.parse(endDateStr);

        if (user.isPresent() && classroom.isPresent() && (startDate.isBefore(endDate) || startDate.equals(endDate)) && user.get().isAuthorized()) {
            LocalDate currentDate = startDate;
            boolean allReservationsSuccessful = true;

            while (!currentDate.isAfter(endDate)) {
                boolean isSlotAvailable = !hasReservation(classroomId, currentDate);

                if (isSlotAvailable) {
                    currentDate = currentDate.plusDays(1);
                } else {
                    allReservationsSuccessful = false;
                    break;
                }
            }

            if (allReservationsSuccessful) {
                currentDate = startDate;

                while (!currentDate.isAfter(endDate)) {
                    Reservation reservation = new Reservation();
                    reservation.setUser(user.get());
                    reservation.setClassroom(classroom.get());
                    reservation.setDate(currentDate);
                    reservationRepository.save(reservation);
                    currentDate = currentDate.plusDays(1);
                }
            }

            return allReservationsSuccessful;
        }

        return false;
    }

    public boolean hasReservation(Long classroomId, LocalDate date) {
        // Comment: Check if a classroom has a reservation on a specific date.
        List<Reservation> reservations = reservationRepository.findByClassroom_IdAndDate(classroomId, date);
        return !reservations.isEmpty();
    }

    /**
     * Searches for classroom availability.
     * @param classroomId Classroom ID
     * @return List of classroom availabilities.
     */
    @Override
    public List<ClassroomAvailabilityDto> classroomAvailabilitiesList(Long classroomId) {
        Optional<Classroom> classroom = classroomService.findById(classroomId);
        if (classroom.isPresent()) {
            List<ClassroomAvailabilityDto> classroomAvailabilities = new ArrayList<>();
            LocalDate now = LocalDate.now();
            LocalDate endDate = now.plusDays(30);

            while (now.isBefore(endDate)) {
                ClassroomAvailabilityDto classroomAvailability = new ClassroomAvailabilityDto();
                classroomAvailability.setClassroomName(classroom.get().getName());
                classroomAvailability.setClassroomId(classroom.get().getId());
                classroomAvailability.setDate(now);

                if (hasReservation(classroomId, now)) {
                    classroomAvailability.setNotAvailable(true);
                }

                classroomAvailabilities.add(classroomAvailability);
                now = now.plusDays(1);
            }

            return classroomAvailabilities;
        }

        return Collections.emptyList();
    }
}
