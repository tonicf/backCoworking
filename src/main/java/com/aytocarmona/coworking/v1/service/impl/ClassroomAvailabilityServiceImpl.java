package com.aytocarmona.coworking.v1.service.impl;


import com.aytocarmona.coworking.v1.model.ClassroomAvailability;
import com.aytocarmona.coworking.v1.repository.ClassroomAvailabilityRepository;
import com.aytocarmona.coworking.v1.service.ClassroomAvailabilityService;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class ClassroomAvailabilityServiceImpl implements ClassroomAvailabilityService {

    private final ClassroomAvailabilityRepository classroomAvailabilityRepository;


    public ClassroomAvailabilityServiceImpl(ClassroomAvailabilityRepository classroomAvailabilityRepository) {
        this.classroomAvailabilityRepository = classroomAvailabilityRepository;
    }


    @Override
    public void save(ClassroomAvailability classroomAvailability) {

    }

    @Override
    public List<ClassroomAvailability> findAll() {
        return classroomAvailabilityRepository.findAll();
    }

    @Override
    public Optional<ClassroomAvailability> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {

    }
}
