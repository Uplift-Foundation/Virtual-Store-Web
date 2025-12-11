package com.upliftfoundation.virtualstoreweb.DataHandlers.RepoDataHandlers;

import com.upliftfoundation.virtualstoreweb.DataHandlers.Repository.StoreItemRepository;
import com.upliftfoundation.virtualstoreweb.ProductObjects.BookObjects.ComicBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComicBookDeleteData extends RepoDeleteData<ComicBook> {

    @Autowired
    public StoreItemRepository<ComicBook> storeItemRepository;
}
