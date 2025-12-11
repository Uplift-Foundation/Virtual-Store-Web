package com.upliftfoundation.virtualstoreweb.DataHandlers.Repository;

import com.upliftfoundation.virtualstoreweb.ProductObjects.Tshirt;

import org.springframework.stereotype.Repository;

@Repository
public interface TshirtRepository<T extends Tshirt> extends StoreItemRepository<T>{

}