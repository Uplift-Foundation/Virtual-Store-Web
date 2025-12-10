package com.upliftfoundation.virtualstoreweb.DataHandlers.RepoDataHandlers;

import java.util.UUID;

import com.upliftfoundation.virtualstoreweb.DataHandlers.ICreateData;
import com.upliftfoundation.virtualstoreweb.DataHandlers.Repository.StoreItemRepository;
import com.upliftfoundation.virtualstoreweb.FundamentalObjects.StoreItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RepoCreateData<T extends StoreItem> implements ICreateData {

    @Autowired
    private StoreItemRepository<T> storeItemRepository;

    @Override
    public UUID Create(StoreItem item) {
        return this.storeItemRepository.save((T) item).getID();
    }
    
}
