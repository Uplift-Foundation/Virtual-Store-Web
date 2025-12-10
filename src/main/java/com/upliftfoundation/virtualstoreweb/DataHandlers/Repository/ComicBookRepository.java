package com.upliftfoundation.virtualstoreweb.DataHandlers.Repository;

import com.upliftfoundation.virtualstoreweb.ProductObjects.BookObjects.ComicBook;

import org.springframework.stereotype.Repository;

@Repository
public interface ComicBookRepository<T extends ComicBook> extends StoreItemRepository<T> {   
}