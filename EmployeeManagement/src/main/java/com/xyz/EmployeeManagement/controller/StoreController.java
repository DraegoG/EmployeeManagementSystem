package com.xyz.EmployeeManagement.controller;

import com.xyz.EmployeeManagement.model.Department;
import com.xyz.EmployeeManagement.model.Store;
import com.xyz.EmployeeManagement.service.StoreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stores")
public class StoreController {

    private final StoreService storeService;

    //injecting the dependency using constructor injection
    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @PostMapping
    public ResponseEntity<Store> addStore(@RequestBody Store store) {
        Store store1 = storeService.addStoreToMap(store);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(store1);
    }

    @PostMapping("/{storeId}/department")
    public ResponseEntity<Store> addDepartment(@RequestBody Department department, @PathVariable(value = "storeId") Long storeId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(storeService.addDepartmentToStore(department, storeId));
    }

    @GetMapping
    public ResponseEntity<List<Store>> getAllStore() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(storeService.getAllStoreList());
    }


}
