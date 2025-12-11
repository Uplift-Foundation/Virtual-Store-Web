package com.upliftfoundation.virtualstoreweb.Controllers;

import java.util.List;
import java.util.UUID;

import com.upliftfoundation.virtualstoreweb.DataHandlers.RepoDataHandlers.RepoCreateData;
import com.upliftfoundation.virtualstoreweb.DataHandlers.RepoDataHandlers.RepoDeleteData;
import com.upliftfoundation.virtualstoreweb.DataHandlers.RepoDataHandlers.RepoReadData;
import com.upliftfoundation.virtualstoreweb.DataHandlers.RepoDataHandlers.RepoUpdateData;
import com.upliftfoundation.virtualstoreweb.FundamentalObjects.StoreItem;
import com.upliftfoundation.virtualstoreweb.ProductObjects.BookObjects.ComicBook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class ComicBookRestController {
    
    @Autowired
    RepoCreateData<ComicBook> dataHandler;
    @Autowired
    RepoUpdateData<ComicBook> dataHandlerUpdate;
    @Autowired
    RepoDeleteData<ComicBook> dataHandlerDelete;
    @Autowired
    RepoReadData<ComicBook> dataHandlerRead;

    @PostMapping("/comicbooks")
    ComicBook newComicBook(@RequestBody ComicBook newComicBook) {
        UUID newId = dataHandler.Create(newComicBook);
        return newComicBook;
    }
    
    @GetMapping("/comicbooks")
    List<StoreItem> allComicbooks() {
        return dataHandlerRead.ReadAll();
    }

    @GetMapping("/comicbooks/{id}")
    ComicBook getComicbook(@PathVariable String id) {
        ComicBook comicbook = (ComicBook) dataHandlerRead.Read(UUID.fromString(id));
        return comicbook;
    }
    
    @PutMapping("/comicbooks/{id}")
    ComicBook replaceComicBook(@RequestBody ComicBook newComicBook, @PathVariable String id) throws Exception {
        ComicBook comicbook = (ComicBook) dataHandlerRead.Read(UUID.fromString(id));
        if (comicbook != null) {
            newComicBook.setID(comicbook.getID());
            dataHandlerUpdate.Update(newComicBook);
            return newComicBook;
        }
        else {
            throw new Exception("No comicbook found with id: " + id);
        }
    }
    
    @DeleteMapping("/comicbooks/{id}")
    void deleteComicBook(@PathVariable String id) {
        dataHandlerDelete.Delete(UUID.fromString(id));
    }
}