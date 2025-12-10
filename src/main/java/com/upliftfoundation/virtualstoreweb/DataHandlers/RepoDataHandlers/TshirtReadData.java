package com.upliftfoundation.virtualstoreweb.DataHandlers.RepoDataHandlers;

import java.util.ArrayList;
import java.util.List;

import com.upliftfoundation.virtualstoreweb.DataHandlers.Repository.StoreItemRepository;
import com.upliftfoundation.virtualstoreweb.FundamentalObjects.StoreItem;
import com.upliftfoundation.virtualstoreweb.ProductObjects.Tshirt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TshirtReadData extends RepoReadData<Tshirt> {

    @Autowired
    public StoreItemRepository<Tshirt> storeItemRepository;

    @Override
    public List<StoreItem> ReadAll() {
        ArrayList<StoreItem> listOfItemsToReturn = new ArrayList<StoreItem>();
        for (Tshirt tshirt : this.storeItemRepository.findAll()) {
            listOfItemsToReturn.add(tshirt);
        }
        return listOfItemsToReturn;
    }   
}