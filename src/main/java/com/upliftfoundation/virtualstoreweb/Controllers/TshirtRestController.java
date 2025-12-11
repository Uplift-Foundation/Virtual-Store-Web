package com.upliftfoundation.virtualstoreweb.Controllers;

import java.util.List;
import java.util.UUID;

import com.upliftfoundation.virtualstoreweb.DataHandlers.RepoDataHandlers.RepoCreateData;
import com.upliftfoundation.virtualstoreweb.DataHandlers.RepoDataHandlers.RepoDeleteData;
import com.upliftfoundation.virtualstoreweb.DataHandlers.RepoDataHandlers.RepoReadData;
import com.upliftfoundation.virtualstoreweb.DataHandlers.RepoDataHandlers.RepoUpdateData;
import com.upliftfoundation.virtualstoreweb.FundamentalObjects.StoreItem;
import com.upliftfoundation.virtualstoreweb.ProductObjects.Tshirt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class TshirtRestController {
    
    @Autowired
    RepoCreateData<Tshirt> dataHandler;
    @Autowired
    RepoUpdateData<Tshirt> dataHandlerUpdate;
    @Autowired
    RepoDeleteData<Tshirt> dataHandlerDelete;
    @Autowired
    RepoReadData<Tshirt> dataHandlerRead;

   @PostMapping("/tshirt")
    Tshirt newTshirt(@RequestBody Tshirt newTshirt) {
        UUID newId = dataHandler.Create(newTshirt);
        return newTshirt;
    }
    
    @GetMapping("/tshirt")
    List<StoreItem> allTshirt() {
        return dataHandlerRead.ReadAll();
    }

    @GetMapping("/tshirt/{id}")
    Tshirt getTshirt(@PathVariable String id) {
        Tshirt tshirt = (Tshirt) dataHandlerRead.Read(UUID.fromString(id));
        return tshirt;
    }
    
    public TshirtRestController() {
    }

    @PutMapping("/tshirt/{id}")
    Tshirt replaceTshirt(@RequestBody Tshirt newTshirt, @PathVariable String id) throws Exception {
        Tshirt tshirt = (Tshirt) dataHandlerRead.Read(UUID.fromString(id));
        if (tshirt != null) {
            newTshirt.setID(tshirt.getID());
            dataHandlerUpdate.Update(newTshirt);
            return newTshirt;
        }
        else {
            throw new Exception("No tshirt found with id: " + id);
        }
    }
    
    @DeleteMapping("/tshirt/{id}")
    void deleteTshirt(@PathVariable String id) {
        dataHandlerDelete.Delete(UUID.fromString(id));
    }
}