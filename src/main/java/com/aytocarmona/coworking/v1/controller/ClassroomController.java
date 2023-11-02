package com.aytocarmona.coworking.v1.controller;

import com.aytocarmona.coworking.v1.dto.ClassroomDto;
import com.aytocarmona.coworking.v1.model.Classroom;
import com.aytocarmona.coworking.v1.model.ClassroomAvailability;
import com.aytocarmona.coworking.v1.service.ClassroomAvailabilityService;
import com.aytocarmona.coworking.v1.service.ClassroomService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("${api.version}/classrooms")  // Base path for all classroom operations
public class ClassroomController {

    private final ClassroomService classroomService;

    /**
     * Constructor for the ClassroomController class.
     *
     * @param classroomService The service for managing classrooms.
     */
    public ClassroomController(ClassroomService classroomService) {
        this.classroomService = classroomService;
    }

    /**
     * Save a new classroom in the database.
     *
     * @param classroomDto The Classroom object to be saved.
     */
    @PostMapping
    public void save(@RequestBody ClassroomDto classroomDto) {
        classroomService.save(classroomDto);
    }

    /**
     * Get a list of all available classrooms.
     *
     * @return A list of Classroom objects.
     */
    @GetMapping
    public List<Classroom> findAll() {
        return classroomService.findAll();
    }

    /**
     * Get a classroom by its ID.
     *
     * @param id The ID of the classroom to be retrieved.
     * @return An Optional containing the classroom if found, or empty if not.
     */
    @GetMapping("/{id}")
    public Optional<Classroom> findById(@PathVariable Long id) {
        return classroomService.findById(id);
    }

    /**
     * Delete a classroom by its ID.
     *
     * @param id The ID of the classroom to be deleted.
     */
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        classroomService.deleteById(id);
    }
}

