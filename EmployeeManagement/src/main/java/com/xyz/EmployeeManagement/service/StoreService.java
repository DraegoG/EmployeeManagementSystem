package com.xyz.EmployeeManagement.service;

import com.xyz.EmployeeManagement.model.Department;
import com.xyz.EmployeeManagement.model.Store;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class StoreService {

    Map<Long, Store> storeMap = new ConcurrentHashMap<Long, Store>();

    public Map<Long, Store> getStoreMap() {
        return storeMap;
    }

    public Store addStoreToMap(Store newStore) {
        storeMap.put(newStore.getId(), newStore);
        return newStore;
    }

    public Store addDepartmentToStore(Department department, Long storeId) {
        if (storeMap.containsKey(storeId)) {
            storeMap.get(storeId).getDepartmentsList().add(department);
        } else {
//            throw new CustomException("Store with id: " + storeId + " does not exist");
        }
        return storeMap.get(storeId);
    }

    public List<Store> getAllStoreList() {
        return storeMap.values().stream().toList();
    }
}
