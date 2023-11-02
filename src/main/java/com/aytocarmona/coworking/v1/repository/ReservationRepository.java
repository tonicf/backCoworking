package com.aytocarmona.coworking.v1.repository;

import com.aytocarmona.coworking.v1.dto.BookingDto;
import com.aytocarmona.coworking.v1.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    /**
     * Retrieve reservations by classroom ID, date, and time slot.
     *
     * @param classroomId The ID of the classroom.
     * @param date       The date of the reservation.
     * @param timeSlot   The time slot of the reservation.
     * @return A list of reservations that match the criteria.
     */
    @Query(value = "SELECT * FROM reservations " +
            "WHERE classroom_id = :classroomId " +
            "AND date = :date " +
            "AND time_slot = :timeSlot",
            nativeQuery = true)
    List<Reservation> findByClassroom_IdAndDateAndTimeSlot(
            Long classroomId, LocalDate date, String timeSlot);

    /**
     * Retrieve reservations by classroom ID and date.
     *
     * @param classroomId The ID of the classroom.
     * @param date       The date of the reservation.
     * @return A list of reservations that match the criteria.
     */
    @Query(value = "SELECT * FROM reservations " +
            "WHERE classroom_id = :classroomId " +
            "AND date = :date ",
            nativeQuery = true)
    List<Reservation> findByClassroom_IdAndDate(
            Long classroomId, LocalDate date);

    @Query(value = "SELECT * FROM reservations " +
            "WHERE user_id = :userId", nativeQuery = true)
    Optional<Reservation> findByUser_Id(
            Long userId);
}

