package com.upliftfoundation.virtualstoreweb.DataHandlers.Repository;

import com.upliftfoundation.virtualstoreweb.ProductObjects.Powerplant;

import org.springframework.stereotype.Repository;

@Repository
public interface PowerplantRepository<T extends Powerplant> extends StoreItemRepository<T> {   
}