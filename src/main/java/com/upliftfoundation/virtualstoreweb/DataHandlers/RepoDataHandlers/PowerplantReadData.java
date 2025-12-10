package com.upliftfoundation.virtualstoreweb.DataHandlers.RepoDataHandlers;

import java.util.ArrayList;
import java.util.List;

import com.upliftfoundation.virtualstoreweb.DataHandlers.Repository.StoreItemRepository;
import com.upliftfoundation.virtualstoreweb.FundamentalObjects.StoreItem;
import com.upliftfoundation.virtualstoreweb.ProductObjects.Powerplant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PowerplantReadData extends RepoReadData<Powerplant> {

    @Autowired
    public StoreItemRepository<Powerplant> storeItemRepository;

    @Override
    public List<StoreItem> ReadAll() {
        ArrayList<StoreItem> listOfItemsToReturn = new ArrayList<StoreItem>();
        for (Powerplant powerplant : this.storeItemRepository.findAll()) {
            listOfItemsToReturn.add(powerplant);
        }
        return listOfItemsToReturn;
    }   
}
