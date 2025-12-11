package com.upliftfoundation.virtualstoreweb.DataHandlers.RepoDataHandlers;

import com.upliftfoundation.virtualstoreweb.DataHandlers.Repository.StoreItemRepository;
import com.upliftfoundation.virtualstoreweb.ProductObjects.Tshirt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TshirtDeleteData extends RepoDeleteData<Tshirt> {

    @Autowired
    public StoreItemRepository<Tshirt> storeItemRepository;
}