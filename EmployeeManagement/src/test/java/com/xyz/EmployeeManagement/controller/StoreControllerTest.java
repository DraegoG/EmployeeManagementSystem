package com.xyz.EmployeeManagement.controller;

import com.xyz.EmployeeManagement.model.Store;
import com.xyz.EmployeeManagement.service.StoreService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StoreControllerTest {

    @InjectMocks
    private StoreController storeController;

    @Mock
    private StoreService storeService;

    @Test
    public void givenAddStoreWhenAddingAStoreAndRequestSucceedsShouldReturnHttpOkStatusCodeAndStoreAsResponse() {
        Store store = new Store(1000L, "S1", "Delhi");

        when(storeService.addStoreToMap(store)).thenReturn(store);

        assertEquals(store, storeController.addStore(store).getBody());
        assertEquals(HttpStatus.CREATED, storeController.addStore(store).getStatusCode());
    }

    @Test
    public void givenGetAllStoreWhen2ExistingStoresInMapShouldReturn2StoresAsResponseWithStatusCodeAsHttpOk() {
        Store s1 = new Store(1000L, "S1", "Delhi");
        Store s2 = new Store(2000L, "S2", "Mumbai");

        when(storeService.getAllStoreList()).thenReturn(Arrays.asList(s1, s2));

        assertEquals(HttpStatus.OK, storeController.getAllStore().getStatusCode());
        assertTrue(storeController.getAllStore().getBody().containsAll(Arrays.asList(s1, s2)));
    }
}