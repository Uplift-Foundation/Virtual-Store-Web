package com.upliftfoundation.virtualstoreweb.Controllers;

import java.util.List;
import java.util.UUID;

import com.upliftfoundation.virtualstoreweb.DataHandlers.RepoDataHandlers.RepoCreateData;
import com.upliftfoundation.virtualstoreweb.DataHandlers.RepoDataHandlers.RepoDeleteData;
import com.upliftfoundation.virtualstoreweb.DataHandlers.RepoDataHandlers.RepoReadData;
import com.upliftfoundation.virtualstoreweb.DataHandlers.RepoDataHandlers.RepoUpdateData;
import com.upliftfoundation.virtualstoreweb.FundamentalObjects.StoreItem;
import com.upliftfoundation.virtualstoreweb.ProductObjects.BookObjects.Book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookRestController {
    
    @Autowired
    RepoCreateData<Book> dataHandler;
    @Autowired
    RepoUpdateData<Book> dataHandlerUpdate;
    @Autowired
    RepoDeleteData<Book> dataHandlerDelete;
    @Autowired
    RepoReadData<Book> dataHandlerRead;

    @PostMapping("/books")
    Book newBook(@RequestBody Book newBook) {
        UUID newId = dataHandler.Create(newBook);
        return newBook;
    }
    
    @GetMapping("/books")
    List<StoreItem> allBooks() {
        return dataHandlerRead.ReadAll();
    }

    @GetMapping("/books/{id}")
    Book getBook(@PathVariable String id) {
        Book book = (Book) dataHandlerRead.Read(UUID.fromString(id));
        return book;
    }

    @PutMapping("/books/{id}")
    Book replaceBook(@RequestBody Book newBook, @PathVariable String id) throws Exception {
        Book book = (Book) dataHandlerRead.Read(UUID.fromString(id));
        if (book != null) {
            newBook.setID(book.getID());
            dataHandlerUpdate.Update(newBook);
            return newBook(newBook);
        }
        else {
            throw new Exception("No book found with id: " + id);
        }  
    }

    @DeleteMapping("/books/{id}")
    void deleteBook(@PathVariable String id) {
        dataHandlerDelete.Delete(UUID.fromString(id));
    }
}
