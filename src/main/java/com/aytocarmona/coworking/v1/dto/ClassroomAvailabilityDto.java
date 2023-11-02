package com.aytocarmona.coworking.v1.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;
@Data
public class ClassroomAvailabilityDto {

    private LocalDate date;
    private String classroomName;
    private Long classroomId;
    private boolean isNotAvailable;

    public ClassroomAvailabilityDto() {
    }

    /**
     * Constructor for the ClassroomAvailabilityDto class.
     *
     * @param date         The date of availability.
     * @param classroomName The name of the classroom.
     * @param classroomId   The ID of the classroom.
     * @param isNotAvailable A boolean indicating if the classroom is not available.
     */
    public ClassroomAvailabilityDto(LocalDate date, String classroomName, Long classroomId, boolean isNotAvailable) {
        this.date = date;
        this.classroomId = classroomId;
        this.classroomName = classroomName;
        this.isNotAvailable = isNotAvailable;
    }
}

