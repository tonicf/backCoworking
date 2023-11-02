package com.aytocarmona.coworking.v1.dto;

public class BookingDto {
    private Long userId;
    private Long classroomId;
    private String startDate;
    private String endDate;

    public BookingDto(Long userId, Long classroomId, String startDate, String endDate) {
        this.userId = userId;
        this.classroomId = classroomId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(Long classroomId) {
        this.classroomId = classroomId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
