package com.upliftfoundation.virtualstoreweb.DataHandlers.Repository;

import com.upliftfoundation.virtualstoreweb.ProductObjects.Drawing;

import org.springframework.stereotype.Repository;

@Repository
public interface DrawingRepository<T extends Drawing> extends StoreItemRepository<T>{

}