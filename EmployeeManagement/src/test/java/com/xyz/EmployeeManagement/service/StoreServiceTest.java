package com.xyz.EmployeeManagement.service;

import com.xyz.EmployeeManagement.Exception.CustomException;
import com.xyz.EmployeeManagement.model.Department;
import com.xyz.EmployeeManagement.model.Store;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class StoreServiceTest {

    @InjectMocks
    StoreService storeService;

    @Test
    public void givenAddStoreToMapWhen1StoreAddedShouldAddStoreToStoreMapAndIncreaseStoreMapSizeBy1AndReturnStoredStore() {
        int initStoreMapSize = storeService.getStoreMap().size();

        Store s1 = new Store(1000L, "S1", "Delhi");
        Store storedStore = storeService.addStoreToMap(s1);

        int finalStoreMapSize = storeService.getStoreMap().size();

        assertEquals(1, finalStoreMapSize - initStoreMapSize);
        assertEquals(storedStore, s1);
    }

    @Test
    public void givenGetAllStoreListWhen2StoresExistShouldReturn2Stores() {
        Store s1 = new Store(1001L, "S1", "Delhi");
        Store s2 = new Store(1002L, "S2", "Bangalore");

        storeService.addStoreToMap(s1);
        storeService.addStoreToMap(s2);

        List<Store> existingStoreList = storeService.getAllStoreList();

        assertEquals(2, existingStoreList.size());
        assertTrue(existingStoreList.containsAll(Arrays.asList(s1, s2)));
    }

    @Test
    public void givenAddDepartmentToStoreWhen2DepartmentsAddedToStoreShouldAddDepartmentsToStoreAndReturnStore() {
        long storeId = 1000L;
        Store s1 = new Store(storeId, "S1", "Delhi");
        storeService.addStoreToMap(s1);

        Department d1 = new Department(20L, "D1", LocalTime.of(7, 0), LocalTime.of(10, 0));
        Department d2 = new Department(30L, "D2", LocalTime.of(11, 0), LocalTime.of(13, 0));

        storeService.addDepartmentToStore(d1, storeId);
        Store storeSvcResp = storeService.addDepartmentToStore(d2, storeId);

        assertEquals(2, storeSvcResp.getDepartmentsList().size());
    }

    @Test
    public void givenAddDepartmentToStoreWhen1DepartmentAddedToStoreWhichDoesNotExistShouldThrowCustomException() {
        long storeIdWhichDoesNotExist = 1000L;
        Department d1 = new Department(20L, "D1", LocalTime.of(7, 0), LocalTime.of(10, 0));

        CustomException customException = assertThrows(CustomException.class, () -> storeService.addDepartmentToStore(d1, storeIdWhichDoesNotExist));

        assertEquals(customException.getHttpStatus(), HttpStatus.NOT_FOUND);
        assertEquals(customException.getErrorMessage(), "Store with id: " + storeIdWhichDoesNotExist + " does not exist");

    }
}