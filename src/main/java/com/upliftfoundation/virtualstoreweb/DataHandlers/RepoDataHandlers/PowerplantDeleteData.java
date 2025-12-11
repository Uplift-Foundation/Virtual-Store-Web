package com.upliftfoundation.virtualstoreweb.DataHandlers.RepoDataHandlers;

import com.upliftfoundation.virtualstoreweb.DataHandlers.Repository.StoreItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.upliftfoundation.virtualstoreweb.ProductObjects.Powerplant;
import org.springframework.stereotype.Service;

@Service
public class PowerplantDeleteData extends RepoDeleteData<Powerplant> {

    @Autowired
    public StoreItemRepository<Powerplant> storeItemRepository;
}
