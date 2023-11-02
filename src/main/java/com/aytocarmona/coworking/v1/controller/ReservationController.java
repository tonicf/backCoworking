package com.aytocarmona.coworking.v1.controller;

import com.aytocarmona.coworking.v1.dto.ClassroomAvailabilityDto;
import com.aytocarmona.coworking.v1.dto.BookingDto;
import com.aytocarmona.coworking.v1.model.Reservation;
import com.aytocarmona.coworking.v1.model.User;
import org.json.JSONObject;

import com.aytocarmona.coworking.v1.service.ReservationService;
import jakarta.annotation.security.PermitAll;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("${api.version}/reservation")
public class ReservationController {

    private final ReservationService reservationService;

    /**
     * Constructor for the ReservationController class.
     *
     * @param reservationService The service for managing reservations.
     */
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    /**
     * Book a reservation for a classroom.
     *
     * @param booking data booking
     * @return A ResponseEntity with a success message or an error message.
     */
    @PreAuthorize(value = "isAuthenticated()")
    @PostMapping()
    public ResponseEntity<String> booking(
            @RequestBody BookingDto booking
    ) {
        boolean success = reservationService.booking(booking.getUserId(), booking.getClassroomId(), booking.getStartDate(), booking.getEndDate());
        if (success) {
            JSONObject response = new JSONObject();
            response.put("message", "Reservation created successfully");
            return ResponseEntity.ok(response.toString());
        } else {
            JSONObject response = new JSONObject();
            response.put("message", "Failed to create the reservation");
            return ResponseEntity.badRequest().body(response.toString());
        }
    }

    /**
     * Get a list of hourly availabilities for a classroom.
     *
     * @param classroomId The ID of the classroom.
     * @return List of ClassroomAvailabilityDto objects.
     */
    @PreAuthorize(value = "isAuthenticated()")
    @GetMapping("/availability/{classroomId}")
    public List<ClassroomAvailabilityDto> classroomAvailabilitiesList(@PathVariable Long classroomId) {
        return reservationService.classroomAvailabilitiesList(classroomId);
    }

    @PreAuthorize(value = "isAuthenticated()")
    @GetMapping("/{userId}")
    public Optional<Reservation> findById(@PathVariable Long userId) {
        return reservationService.findById(userId);
    }

}
