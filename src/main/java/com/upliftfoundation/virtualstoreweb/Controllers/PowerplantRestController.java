package com.upliftfoundation.virtualstoreweb.Controllers;

import java.util.List;
import java.util.UUID;

import com.upliftfoundation.virtualstoreweb.DataHandlers.RepoDataHandlers.RepoCreateData;
import com.upliftfoundation.virtualstoreweb.DataHandlers.RepoDataHandlers.RepoDeleteData;
import com.upliftfoundation.virtualstoreweb.DataHandlers.RepoDataHandlers.RepoReadData;
import com.upliftfoundation.virtualstoreweb.DataHandlers.RepoDataHandlers.RepoUpdateData;
import com.upliftfoundation.virtualstoreweb.FundamentalObjects.StoreItem;
import com.upliftfoundation.virtualstoreweb.ProductObjects.Powerplant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class PowerplantRestController {
    
    @Autowired
    RepoCreateData<Powerplant> dataHandler;
    @Autowired
    RepoUpdateData<Powerplant> dataHandlerUpdate;
    @Autowired
    RepoDeleteData<Powerplant> dataHandlerDelete;
    @Autowired
    RepoReadData<Powerplant> dataHandlerRead;

    @PostMapping("/powerplants")
    Powerplant newPowerplant(@RequestBody Powerplant newPowerplant) {
        UUID newId = dataHandler.Create(newPowerplant);
        return newPowerplant;
    }
    
    @GetMapping("/powerplants")
    List<StoreItem> allPowerplants() {
        return dataHandlerRead.ReadAll();
    }

    @GetMapping("/powerplants/{id}")
    Powerplant getPowerplant(@PathVariable String id) {
        Powerplant powerplant = (Powerplant) dataHandlerRead.Read(UUID.fromString(id));
        return powerplant;
    }
    
    @PutMapping("/powerplants/{id}")
    Powerplant replacePowerplant(@RequestBody Powerplant newPowerplant, @PathVariable String id) throws Exception {
        Powerplant powerplant = (Powerplant) dataHandlerRead.Read(UUID.fromString(id));
        if (powerplant != null) {
            newPowerplant.setID(powerplant.getID());
            dataHandlerUpdate.Update(newPowerplant);
            return newPowerplant;
        }
        else {
            throw new Exception("No energy found with id: " + id);
        }
    }

    @DeleteMapping("/powerplants/{id}")
    void deletePowerplant(@PathVariable String id) {
        dataHandlerDelete.Delete(UUID.fromString(id));
    }
}