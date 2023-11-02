package com.aytocarmona.coworking.v1.repository;

import com.aytocarmona.coworking.v1.model.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for the Classroom entity.
 * Provides methods for performing CRUD operations on the Classroom entity.
 */
public interface ClassroomRepository extends JpaRepository<Classroom, Long> {
}


