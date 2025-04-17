package com.xyz.EmployeeManagement.service;

import com.xyz.EmployeeManagement.model.Employee;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @InjectMocks
    EmployeeService employeeService;

    @Test
    public void givenAddEmployeeToMapWhenAddingANewEmployeeShouldIncreaseTheMapSizeBy1AndPersistAndReturnNewEmployeeInfo() {

        Long storeId = 1000L;
        Long empId = 1L;

        int initialEmployeeMapSize = employeeService.getEmployeeMap().size();

        Employee newEmployee = new Employee(empId, "Abhishek", storeId);

        Employee employeeAdded = employeeService.addEmployeeToMap(newEmployee);

        int finalEmployeeMapSize = employeeService.getEmployeeMap().size();

        //assertions
        assertEquals(1, finalEmployeeMapSize - initialEmployeeMapSize);

        Employee empFromMap = employeeService.getEmployeeMap().get(empId);
        assertEquals(newEmployee, empFromMap); //validating if the employee has been successfully added in map
        assertEquals(newEmployee, employeeAdded); //validating if the response is as expected
    }

    @Test
    public void givenGetEmployeeByIdWhenEmployeeExistsInMapShouldReturnEmployeeInfo() {
        Long storeId = 1000L;
        Long empId = 1L;
        Employee newEmployee = new Employee(empId, "Abhishek", storeId);

        employeeService.addEmployeeToMap(newEmployee);

        //assertions
        Employee empFromMap = employeeService.getEmployeeById(empId);
        assertEquals(newEmployee, empFromMap); //validating if the response is as expected
    }


}