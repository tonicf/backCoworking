package com.aytocarmona.coworking.v1.service.impl;

import com.aytocarmona.coworking.v1.dto.ClassroomDto;
import com.aytocarmona.coworking.v1.model.Classroom;
import com.aytocarmona.coworking.v1.repository.ClassroomRepository;
import com.aytocarmona.coworking.v1.service.ClassroomService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Implementation of the service for the Classroom entity.
 */
@Service
public class ClassroomServiceImpl implements ClassroomService {
    private final ClassroomRepository classroomRepository;
    public ClassroomServiceImpl(ClassroomRepository classroomRepository) {
        this.classroomRepository = classroomRepository;
    }

    @Override
    public void save(ClassroomDto classroomDto) {
    Classroom classroom = new Classroom();
        classroom.setId(classroomDto.getId());
        classroom.setName(classroomDto.getName());
        classroomRepository.save(classroom);

    }


    @Override
    public List<Classroom> findAll() {
        return classroomRepository.findAll();
    }

    @Override
    public Optional<Classroom> findById(Long id) {
        return classroomRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        classroomRepository.deleteById(id);
    }
}
