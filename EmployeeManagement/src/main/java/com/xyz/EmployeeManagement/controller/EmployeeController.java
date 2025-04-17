package com.xyz.EmployeeManagement.controller;

import com.xyz.EmployeeManagement.model.Employee;
import com.xyz.EmployeeManagement.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    //add an employee
    @PostMapping
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        Employee emp = employeeService.addEmployeeToMap(employee);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(emp);
    }

    //add employee departments
    @PostMapping("/{employeeId}/departments")
    public ResponseEntity<Employee> addEmployeeDepartment(@PathVariable(value = "employeeId") long empId, @RequestBody long[] departments) {
        Employee emp = employeeService.addDepartmentForEmployee(empId, departments);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(emp);
    }

    //get employee working timeslots for the day
    @GetMapping("/{employeeId}/timeslots")
    public ResponseEntity<List<List<String>>> getEmployeeWorkingTimeSlotsForTheDay(@PathVariable(value = "employeeId") long empId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(employeeService.getEmployeeWorkingTimeSlots(empId));
    }

}
