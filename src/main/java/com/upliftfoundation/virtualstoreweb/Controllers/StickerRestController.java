package com.upliftfoundation.virtualstoreweb.Controllers;

import java.util.List;
import java.util.UUID;

import com.upliftfoundation.virtualstoreweb.DataHandlers.RepoDataHandlers.RepoCreateData;
import com.upliftfoundation.virtualstoreweb.DataHandlers.RepoDataHandlers.RepoDeleteData;
import com.upliftfoundation.virtualstoreweb.DataHandlers.RepoDataHandlers.RepoReadData;
import com.upliftfoundation.virtualstoreweb.DataHandlers.RepoDataHandlers.RepoUpdateData;
import com.upliftfoundation.virtualstoreweb.FundamentalObjects.StoreItem;
import com.upliftfoundation.virtualstoreweb.ProductObjects.Sticker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class StickerRestController {
    
    @Autowired
    RepoCreateData<Sticker> dataHandler;
    @Autowired
    RepoUpdateData<Sticker> dataHandlerUpdate;
    @Autowired
    RepoDeleteData<Sticker> dataHandlerDelete;
    @Autowired
    RepoReadData<Sticker> dataHandlerRead;

    @PostMapping("/stickers")
    Sticker newSticker(@RequestBody Sticker newSticker) {
        UUID newId = dataHandler.Create(newSticker);
        return newSticker;
    }
    
    @GetMapping("/stickers")
    List<StoreItem> allStickers() {
        return dataHandlerRead.ReadAll();
    }

    @GetMapping("/stickers/{id}")
    Sticker getSticker(@PathVariable String id) {
        Sticker sticker = (Sticker) dataHandlerRead.Read(UUID.fromString(id));
        return sticker;
    }
    
    @PutMapping("/stickers/{id}")
    Sticker replaceSticker(@RequestBody Sticker newSticker, @PathVariable String id) throws Exception {
        Sticker sticker = (Sticker) dataHandlerRead.Read(UUID.fromString(id));
        if (sticker != null) {
            newSticker.setID(sticker.getID());
            dataHandlerUpdate.Update(newSticker);
            return newSticker;
        }
        else {
            throw new Exception("No Sticker found with id: " + id);
        }
    }
    
    @DeleteMapping("/stickers/{id}")
    void deleteSticker(@PathVariable String id) {
        dataHandlerDelete.Delete(UUID.fromString(id));
    }
}  

