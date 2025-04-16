package com.xyz.EmployeeManagement.model;

import lombok.Data;

import java.time.LocalTime;

@Data
public class Department {

    private long id;
    private String departmentName;
    private LocalTime startTime;
    private LocalTime endTime;


    public Department(long id, String departmentName, LocalTime startTime, LocalTime endTime) {
        this.id = id;
        this.departmentName = departmentName;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
