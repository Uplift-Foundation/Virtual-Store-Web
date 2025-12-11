package com.upliftfoundation.virtualstoreweb.DataHandlers.RepoDataHandlers;

import com.upliftfoundation.virtualstoreweb.DataHandlers.Repository.StoreItemRepository;
import com.upliftfoundation.virtualstoreweb.ProductObjects.Sticker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StickerDeleteData extends RepoDeleteData<Sticker> {

    @Autowired
    public StoreItemRepository<Sticker> storeItemRepository;
}