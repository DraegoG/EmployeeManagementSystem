package com.xyz.EmployeeManagement.model;

import lombok.Data;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Data
public class Store {
    private long id;
    private String name;
    private String location;
    private Map<Long, Department> departmentsMap;

    public Store(long id, String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.departmentsMap = new ConcurrentHashMap<>();
    }

}
