package com.aytocarmona.coworking.v1.repository;

import com.aytocarmona.coworking.v1.model.ClassroomAvailability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ClassroomAvailabilityRepository extends JpaRepository<ClassroomAvailability,Long> {

}
