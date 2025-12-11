package com.upliftfoundation.virtualstoreweb.DataHandlers.Repository;

import com.upliftfoundation.virtualstoreweb.ProductObjects.BookObjects.Book;

import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository<T extends Book> extends StoreItemRepository<T> {

}  
