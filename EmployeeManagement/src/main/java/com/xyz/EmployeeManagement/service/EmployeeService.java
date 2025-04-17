package com.xyz.EmployeeManagement.service;

import com.xyz.EmployeeManagement.exception.CustomException;
import com.xyz.EmployeeManagement.model.Department;
import com.xyz.EmployeeManagement.model.Employee;
import com.xyz.EmployeeManagement.model.Store;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/*
    An employee will be associated to a store and can serve in multiple departments
 */
@Service
public class EmployeeService {

    private final StoreService storeService;
    @Getter
    private Map<Long, Employee> employeeMap = new ConcurrentHashMap<>();

    public EmployeeService(StoreService storeService) {
        this.storeService = storeService;
    }

    public Employee addEmployeeToMap(Employee employee) {
        employeeMap.put(employee.getId(), employee);
        return employee;
    }

    public Employee getEmployeeById(Long empId) {
        return employeeMap.get(empId);
    }

    public Employee addDepartmentForEmployee(long empId, long[] departments) {
        if (employeeMap.containsKey(empId)) {
            employeeMap.get(empId).setDepartmentIdsToServe(Arrays
                    .stream(departments)
                    .boxed()
                    .toList()
            );
            return employeeMap.get(empId);
        } else {
            throw new CustomException(HttpStatus.NOT_FOUND, "Employee with id: " + empId + " not found");
        }
    }

    public List<List<String>> getEmployeeWorkingTimeSlots(long empId) {
        if (employeeMap.containsKey(empId)) {
            return calculateTimeSlotsForEmployee(empId);
        } else {
            throw new CustomException(HttpStatus.NOT_FOUND, "Employee with id: " + empId + " not found");
        }
    }

    private List<List<String>> calculateTimeSlotsForEmployee(long empId) {
        List<List<String>> answer = new ArrayList<>();
        Store store = storeService.getStoreMap().get(employeeMap.get(empId).getStoreId());

        //comparator to provide the departments in sorted order of the start time
        //Eg. for the following departments
//        D1 - 7 AM - 10 AM
//        D2 - 7 PM - 9 PM
//        D3 - 9 AM - 12 PM
//        D4 - 2 PM - 4 PM
//        D5 - 3 PM - 6 PM
        //the returned list will be: D1, D3, D4, D5, D2
        Comparator<Long> comp = (d1, d2) -> {
            if (store.getDepartmentsMap().get(d1).getStartTime().isAfter(store.getDepartmentsMap().get(d2).getStartTime())) {
                return 1;
            } else {
                return -1;
            }
        };

        List<Department> sortedDeptList = employeeMap.get(empId).getDepartmentIdsToServe()
                .stream()
                .sorted(comp)
                .map(deptId -> store.getDepartmentsMap().get(deptId))
                .toList();

        System.out.println("Sorted department list based on start time"+ sortedDeptList);

        //get the end time of the first department in the list
        LocalTime startTime = sortedDeptList.get(0).getStartTime();
        LocalTime endTime = sortedDeptList.get(0).getEndTime();

        for(int i = 1; i < sortedDeptList.size() ; i++) {
            if(endTime.isBefore(sortedDeptList.get(i).getStartTime())){
                answer.add(
                        Arrays.asList(
                                convertTimeToString(startTime),
                                convertTimeToString(endTime)
                        )
                );
                startTime = sortedDeptList.get(i).getStartTime();
            }
            endTime = sortedDeptList.get(i).getEndTime();
        }

        answer.add(
                Arrays.asList(
                        convertTimeToString(startTime),
                        convertTimeToString(endTime)
                )
        );

        return answer;
    }

    private String convertTimeToString(LocalTime time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
        return time.format(formatter);
    }
}
