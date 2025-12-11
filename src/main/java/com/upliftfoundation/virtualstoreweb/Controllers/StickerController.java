package com.upliftfoundation.virtualstoreweb.Controllers;

import java.util.List;
import java.util.UUID;
import java.io.FileOutputStream;
import java.io.IOException;

import com.upliftfoundation.virtualstoreweb.DataHandlers.RepoDataHandlers.RepoCreateData;
import com.upliftfoundation.virtualstoreweb.DataHandlers.RepoDataHandlers.RepoDeleteData;
import com.upliftfoundation.virtualstoreweb.DataHandlers.RepoDataHandlers.RepoReadData;
import com.upliftfoundation.virtualstoreweb.DataHandlers.RepoDataHandlers.RepoUpdateData;
import com.upliftfoundation.virtualstoreweb.FundamentalObjects.StoreItem;
import com.upliftfoundation.virtualstoreweb.ProductObjects.Sticker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequestMapping("sticker")
public class StickerController {

    UUID ID;
    @Autowired
    RepoCreateData<Sticker> dataHandler;
    @Autowired
    RepoUpdateData<Sticker> dataHandlerUpdate;
    @Autowired
    RepoDeleteData<Sticker> dataHandlerDelete;
    @Autowired
    RepoReadData<Sticker> dataHandlerRead;

    @RequestMapping(value = "/", method=RequestMethod.GET)
    public String index()
    {
      return "sticker_index";
    }

    @GetMapping("/createform")
    public String showForm(Model model) {
        Sticker sticker = new Sticker();
        model.addAttribute("sticker", sticker);
        return "create_sticker";
    }

    @GetMapping("/categoryview")
    public String showCategoryView(Model model) {
        List<StoreItem> stickers = dataHandlerRead.ReadAll();
        model.addAttribute("stickers", stickers);
        return "category_stickers";
    }

    @RequestMapping(value = "/createform", method = RequestMethod.POST)
    public String submit(@ModelAttribute("sticker") Sticker sticker, @RequestParam("file") MultipartFile file, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return "error";
        }
        dataHandler.Create(sticker);
        
        MultipartFile multipartFile = file;
        if (multipartFile != null || !multipartFile.isEmpty())
        {   
            String fileName = sticker.getID().toString()+".png";
            try {
                final String imagePath = "src/main/resources/static/images/"; //path
                FileOutputStream output = new FileOutputStream(imagePath+fileName);
                output.write(multipartFile.getBytes());

                //multipartFile.transferTo(new File(fileName));
            } 
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        model.addAttribute("sticker", sticker);
        return "sticker";
    }

    @RequestMapping(value = "/product/{id}", method = RequestMethod.GET)
    public String product(@PathVariable("id") String id, ModelMap model) {
        Sticker sticker = (Sticker) dataHandlerRead.Read(UUID.fromString(id));
        model.addAttribute("sticker", sticker);
        return "sticker_product";
    }

    @RequestMapping(value = "/readform/{id}", method = RequestMethod.GET)
    public String read(@PathVariable("id") String Id, ModelMap model) {
        Sticker sticker = (Sticker) dataHandlerRead.Read(UUID.fromString(Id));
        model.addAttribute("sticker", sticker);
        return "sticker";
    }
    
    @RequestMapping(value = "/updateform/{id}", method = RequestMethod.GET)
    public String showUpdateForm(@PathVariable("id") String Id, Model model) {
        Sticker sticker = (Sticker) dataHandlerRead.Read(UUID.fromString(Id));
        model.addAttribute("sticker", sticker);
        return "create_sticker";
    }

    @RequestMapping(value="/updateform", method = RequestMethod.PUT)
    public String change(Sticker sticker, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return "error";
        }
        dataHandlerUpdate.Update(sticker);
        return "sticker";   
    }

    @GetMapping("/deleteform")
    public String showDeleteForm(Model model) {
        List<StoreItem> showItems = dataHandlerRead.ReadAll();
        model.addAttribute("itemsToDelete", showItems);
        return "delete_sticker";
    }

    @RequestMapping(value = "/deleteform/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") String Id, ModelMap model) {
        try {
            dataHandlerDelete.Delete(UUID.fromString(Id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("Id", Id);
        return "itemdelete";
    }
}