package com.upliftfoundation.virtualstoreweb.DataHandlers.RepoDataHandlers;

import com.upliftfoundation.virtualstoreweb.DataHandlers.Repository.StoreItemRepository;
import com.upliftfoundation.virtualstoreweb.ProductObjects.Basketball;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BasketballDeleteData extends RepoDeleteData<Basketball> {
    @Autowired
    public StoreItemRepository<Basketball> storeItemRepository;
}
