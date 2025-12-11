package com.upliftfoundation.virtualstoreweb.DataHandlers.RepoDataHandlers;

import com.upliftfoundation.virtualstoreweb.DataHandlers.Repository.StoreItemRepository;
import com.upliftfoundation.virtualstoreweb.ProductObjects.Drawing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DrawingDeleteData extends RepoDeleteData<Drawing> {

    @Autowired
    public StoreItemRepository<Drawing> storeItemRepository;
}
