package com.upliftfoundation.virtualstoreweb.DataHandlers.Repository;

import com.upliftfoundation.virtualstoreweb.ProductObjects.Basketball;

import org.springframework.stereotype.Repository;

@Repository
public interface BasketballRepository<T extends Basketball> extends StoreItemRepository<T> {   
}

