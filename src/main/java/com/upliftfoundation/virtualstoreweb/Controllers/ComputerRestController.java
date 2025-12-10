package com.upliftfoundation.virtualstoreweb.Controllers;

import java.util.List;
import java.util.UUID;

import com.upliftfoundation.virtualstoreweb.DataHandlers.RepoDataHandlers.RepoCreateData;
import com.upliftfoundation.virtualstoreweb.DataHandlers.RepoDataHandlers.RepoDeleteData;
import com.upliftfoundation.virtualstoreweb.DataHandlers.RepoDataHandlers.RepoReadData;
import com.upliftfoundation.virtualstoreweb.DataHandlers.RepoDataHandlers.RepoUpdateData;
import com.upliftfoundation.virtualstoreweb.FundamentalObjects.StoreItem;
import com.upliftfoundation.virtualstoreweb.ProductObjects.Computer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class ComputerRestController {
    
    @Autowired
    RepoCreateData<Computer> dataHandler;
    @Autowired
    RepoUpdateData<Computer> dataHandlerUpdate;
    @Autowired
    RepoDeleteData<Computer> dataHandlerDelete;
    @Autowired
    RepoReadData<Computer> dataHandlerRead;

    @PostMapping("/computers")
    Computer newComputer(@RequestBody Computer newComputer) {
        UUID newId = dataHandler.Create(newComputer);
        return newComputer;
    }
    
    @GetMapping("/computers")
    List<StoreItem> allComputers() {
        return dataHandlerRead.ReadAll();
    }

    @GetMapping("/computers/{id}")
    Computer getBasketball(@PathVariable String id) {
        Computer computer = (Computer) dataHandlerRead.Read(UUID.fromString(id));
        return computer;
    }
    
    @PutMapping("/computers/{id}")
    Computer replaceComputer(@RequestBody Computer newComputer, @PathVariable String id) throws Exception {
        Computer computer = (Computer) dataHandlerRead.Read(UUID.fromString(id));
        if (computer != null) {
            newComputer.setID(computer.getID());
            dataHandlerUpdate.Update(newComputer);
            return newComputer;
        }
        else {
            throw new Exception("No computer found with id: " + id);
        }
    }

    @DeleteMapping("/computers/{id}")
    void deleteComputer(@PathVariable String id) {
        dataHandlerDelete.Delete(UUID.fromString(id));
    }
}
