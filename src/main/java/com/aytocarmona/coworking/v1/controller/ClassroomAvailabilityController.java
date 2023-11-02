package com.aytocarmona.coworking.v1.controller;

import com.aytocarmona.coworking.v1.model.ClassroomAvailability;
import com.aytocarmona.coworking.v1.service.ClassroomAvailabilityService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("${api.version}/availability")
public class ClassroomAvailabilityController {

    private final ClassroomAvailabilityService classroomAvailabilityService;

    /**
     * Constructor for the ClassroomAvailabilityController class.
     *
     * @param classroomAvailabilityService The service for managing classroom availability.
     */
    public ClassroomAvailabilityController(ClassroomAvailabilityService classroomAvailabilityService) {
        this.classroomAvailabilityService = classroomAvailabilityService;
    }

    /**
     * Get a list of all classroom availabilities.
     *
     * @return List of ClassroomAvailability objects.
     */
    @GetMapping
    public List<ClassroomAvailability> findAll() {
        return classroomAvailabilityService.findAll();
    }
}
