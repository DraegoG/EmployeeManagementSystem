package com.xyz.EmployeeManagement.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Store {
    private long id;
    private String name;
    private String location;
    private List<Department> departmentsList;
    private List<Long> employeeIdListWorkingInCurrentStore;

    public Store(long id, String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.departmentsList = new ArrayList<>();
    }

}
