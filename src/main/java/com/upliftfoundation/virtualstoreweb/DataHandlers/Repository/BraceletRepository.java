package com.upliftfoundation.virtualstoreweb.DataHandlers.Repository;

import com.upliftfoundation.virtualstoreweb.ProductObjects.Bracelet;

import org.springframework.stereotype.Repository;

@Repository
public interface BraceletRepository<T extends Bracelet> extends StoreItemRepository<T> {   
}
