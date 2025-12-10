package com.upliftfoundation.virtualstoreweb.Controllers;

import java.util.List;
import java.util.UUID;

import com.upliftfoundation.virtualstoreweb.DataHandlers.RepoDataHandlers.RepoCreateData;
import com.upliftfoundation.virtualstoreweb.DataHandlers.RepoDataHandlers.RepoDeleteData;
import com.upliftfoundation.virtualstoreweb.DataHandlers.RepoDataHandlers.RepoReadData;
import com.upliftfoundation.virtualstoreweb.DataHandlers.RepoDataHandlers.RepoUpdateData;
import com.upliftfoundation.virtualstoreweb.FundamentalObjects.StoreItem;
import com.upliftfoundation.virtualstoreweb.ProductObjects.Basketball;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class BasketballRestController {
    
    @Autowired
    RepoCreateData<Basketball> dataHandler;
    @Autowired
    RepoUpdateData<Basketball> dataHandlerUpdate;
    @Autowired
    RepoDeleteData<Basketball> dataHandlerDelete;
    @Autowired
    RepoReadData<Basketball> dataHandlerRead;

    @PostMapping("/basketballs")
    Basketball newBasketball(@RequestBody Basketball newBasketball) {
        UUID newId = dataHandler.Create(newBasketball);
        return newBasketball;
    }
    
    @GetMapping("/basketballs")
    List<StoreItem> allBasketballs() {
        return dataHandlerRead.ReadAll();
    }
    
    @GetMapping("/basketballs/{id}")
    Basketball read(@PathVariable("id") String Id) {
        Basketball basketball = (Basketball) dataHandlerRead.Read(UUID.fromString(Id));
        return basketball;
    }
    
    @PutMapping("/basketballs/{id}")
    Basketball replaceBasketball(@RequestBody Basketball newBasketball, @PathVariable String id) throws Exception {
        Basketball basketball = (Basketball) dataHandlerRead.Read(UUID.fromString(id));
        if (basketball != null) {
            newBasketball.setID(basketball.getID());
            dataHandlerUpdate.Update(newBasketball);
            return newBasketball;
        }
        else {
            throw new Exception("No basketball found with id: " + id);
        }
    }

    @DeleteMapping("/basketballs/{id}")
    void deleteBasketball(@PathVariable String id)
    {
        dataHandlerDelete.Delete(UUID.fromString(id));
    }
}
