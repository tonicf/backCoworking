package com.aytocarmona.coworking.v1.dto;

import lombok.Data;

@Data
public class ClassroomDto {
    private Long id;
    private String name;
    private Long type;

    public ClassroomDto() {
    }

    /**
     * Constructor for the ClassroomDto class.
     *
     * @param id   The ID of the classroom.
     * @param name The name of the classroom.
     * @param type The type of the classroom.
     */
    public ClassroomDto(Long id, String name, Long type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }
}

