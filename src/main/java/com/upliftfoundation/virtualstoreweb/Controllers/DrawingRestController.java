package com.upliftfoundation.virtualstoreweb.Controllers;

import java.util.List;
import java.util.UUID;

import com.upliftfoundation.virtualstoreweb.DataHandlers.RepoDataHandlers.RepoCreateData;
import com.upliftfoundation.virtualstoreweb.DataHandlers.RepoDataHandlers.RepoDeleteData;
import com.upliftfoundation.virtualstoreweb.DataHandlers.RepoDataHandlers.RepoReadData;
import com.upliftfoundation.virtualstoreweb.DataHandlers.RepoDataHandlers.RepoUpdateData;
import com.upliftfoundation.virtualstoreweb.FundamentalObjects.StoreItem;
import com.upliftfoundation.virtualstoreweb.ProductObjects.Drawing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class DrawingRestController {
    
    @Autowired
    RepoCreateData<Drawing> dataHandler;
    @Autowired
    RepoUpdateData<Drawing> dataHandlerUpdate;
    @Autowired
    RepoDeleteData<Drawing> dataHandlerDelete;
    @Autowired
    RepoReadData<Drawing> dataHandlerRead;

    @PostMapping("/drawings")
    Drawing newDrawing(@RequestBody Drawing newDrawing) {
        UUID newId = dataHandler.Create(newDrawing);
        return newDrawing;
    }
    @GetMapping("/drawings")
    List<StoreItem> allDrawings() {
        return dataHandlerRead.ReadAll();
    }

    @GetMapping("/drawings/{id}")
    Drawing getDrawing(@PathVariable String id) {
        Drawing drawing = (Drawing) dataHandlerRead.Read(UUID.fromString(id));
        return drawing;
    }
    
    @PutMapping("/drawings/{id}")
    Drawing replaceDrawing(@RequestBody Drawing newDrawing, @PathVariable String id) throws Exception {
        Drawing drawing = (Drawing) dataHandlerRead.Read(UUID.fromString(id));
        if (drawing != null) {
            newDrawing.setID(drawing.getID());
            dataHandlerUpdate.Update(newDrawing);
            return newDrawing;
        }
        else {
            throw new Exception("No Drawing found with id: " + id);
        }
    }
    
    @DeleteMapping("/drawings/{id}")
    void deleteDrawings(@PathVariable String id) {
        dataHandlerDelete.Delete(UUID.fromString(id));
    }
}