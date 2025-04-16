package com.xyz.EmployeeManagement.model;

import lombok.Data;

import java.util.List;

@Data
public class Employee {

    private long id;
    private String name;
    private List<Department> departmentIdsToServe;

    public Employee(long id, String name, List<Department> departmentIdsToServe) {
        this.id = id;
        this.name = name;
        this.departmentIdsToServe = departmentIdsToServe;
    }
}
