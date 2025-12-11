package com.upliftfoundation.virtualstoreweb.Controllers;

import java.util.List;
import java.util.UUID;

import com.upliftfoundation.virtualstoreweb.DataHandlers.RepoDataHandlers.RepoCreateData;
import com.upliftfoundation.virtualstoreweb.DataHandlers.RepoDataHandlers.RepoDeleteData;
import com.upliftfoundation.virtualstoreweb.DataHandlers.RepoDataHandlers.RepoReadData;
import com.upliftfoundation.virtualstoreweb.DataHandlers.RepoDataHandlers.RepoUpdateData;
import com.upliftfoundation.virtualstoreweb.FundamentalObjects.StoreItem;
import com.upliftfoundation.virtualstoreweb.ProductObjects.Bracelet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class BraceletRestController {
    
    @Autowired
    RepoCreateData<Bracelet> dataHandler;
    @Autowired
    RepoUpdateData<Bracelet> dataHandlerUpdate;
    @Autowired
    RepoDeleteData<Bracelet> dataHandlerDelete;
    @Autowired
    RepoReadData<Bracelet> dataHandlerRead;

    @PostMapping("/bracelets")
    Bracelet newBracelet(@RequestBody Bracelet newBracelet) {
        UUID newId = dataHandler.Create(newBracelet);
        return newBracelet;
    }
    
    @GetMapping("/bracelets")
    List<StoreItem> allBracelets() {
        return dataHandlerRead.ReadAll();
    }

    @GetMapping("/bracelets/{id}")
    Bracelet getBracelet(@PathVariable String id) {
        Bracelet bracelet = (Bracelet) dataHandlerRead.Read(UUID.fromString(id));
        return bracelet;
    }
    
    @PutMapping("/bracelets/{id}")
    Bracelet replaceBracelet(@RequestBody Bracelet newBracelet, @PathVariable String id) throws Exception {
        Bracelet bracelet = (Bracelet) dataHandlerRead.Read(UUID.fromString(id));
        if (bracelet != null) {
            newBracelet.setID(bracelet.getID());
            dataHandlerUpdate.Update(newBracelet);
            return newBracelet;
        }
        else {
            throw new Exception("No bracelet found with id: " + id);
        }
    }
    
    @DeleteMapping("/bracelets/{id}")
    void deleteBracelet(@PathVariable String id) {
        dataHandlerDelete.Delete(UUID.fromString(id));
    }
}