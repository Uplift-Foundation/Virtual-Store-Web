package com.upliftfoundation.virtualstoreweb.DataHandlers.Repository;

import com.upliftfoundation.virtualstoreweb.ProductObjects.Computer;

import org.springframework.stereotype.Repository;

@Repository
public interface ComputerRepository<T extends Computer> extends StoreItemRepository<T> {
    
}
