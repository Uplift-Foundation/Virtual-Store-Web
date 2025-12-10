package com.upliftfoundation.virtualstoreweb.Controllers;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import com.upliftfoundation.virtualstoreweb.DataHandlers.RepoDataHandlers.RepoCreateData;
import com.upliftfoundation.virtualstoreweb.DataHandlers.RepoDataHandlers.RepoDeleteData;
import com.upliftfoundation.virtualstoreweb.DataHandlers.RepoDataHandlers.RepoReadData;
import com.upliftfoundation.virtualstoreweb.DataHandlers.RepoDataHandlers.RepoUpdateData;
import com.upliftfoundation.virtualstoreweb.FundamentalObjects.StoreItem;
import com.upliftfoundation.virtualstoreweb.ProductObjects.Bracelet;

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
@RequestMapping("bracelet")
public class BraceletController {

    UUID ID;
    @Autowired
    RepoCreateData<Bracelet> dataHandler;
    @Autowired
    RepoUpdateData<Bracelet> dataHandlerUpdate;
    @Autowired
    RepoDeleteData<Bracelet> dataHandlerDelete;
    @Autowired
    RepoReadData<Bracelet> dataHandlerRead;

    @RequestMapping(value = "/", method=RequestMethod.GET)
    public String index()
    {
      return "bracelet_index";
    }

    @GetMapping("/categoryview")
    public String showCategoryView(Model model) {
        List<StoreItem> bracelets = dataHandlerRead.ReadAll();
        model.addAttribute("bracelets", bracelets);
        return "category_bracelets";
    }
    
    @GetMapping("/createform")
    public String showForm(Model model) {
        Bracelet bracelet = new Bracelet();
        model.addAttribute("bracelet", bracelet);
        return "create_bracelet";
    }

    @RequestMapping(value = "/createform", method = RequestMethod.POST)
    public String submit(@ModelAttribute("bracelet") Bracelet bracelet, @RequestParam("file") MultipartFile file, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return "error";
        }
        dataHandler.Create(bracelet);
        
        MultipartFile multipartFile = file;
        if (multipartFile != null || !multipartFile.isEmpty())
        {
            String fileName = bracelet.getID().toString()+".png";
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
        model.addAttribute("bracelet", bracelet);
        return "bracelet";
    }

    @RequestMapping(value = "/product/{id}", method = RequestMethod.GET)
    public String product(@PathVariable("id") String id, ModelMap model) {
        Bracelet bracelet = (Bracelet) dataHandlerRead.Read(UUID.fromString(id));
        model.addAttribute("bracelet", bracelet);
        return "bracelet_product";
    }

    @RequestMapping(value = "/readform/{id}", method = RequestMethod.GET)
    public String read(@PathVariable("id") String Id, ModelMap model) {
        Bracelet bracelet = (Bracelet) dataHandlerRead.Read(UUID.fromString(Id));
        model.addAttribute("bracelet", bracelet);
        return "bracelet";
    }
    
    @RequestMapping(value = "/updateform/{id}", method = RequestMethod.GET)
    public String showUpdateForm(@PathVariable("id") String Id, Model model) {
        Bracelet bracelet = (Bracelet) dataHandlerRead.Read(UUID.fromString(Id));
        model.addAttribute("bracelet", bracelet);
        return "create_bracelet";
    }

    @RequestMapping(value="/updateform", method = RequestMethod.PUT)
    public String change(Bracelet bracelet, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return "error";
        }
        dataHandlerUpdate.Update(bracelet);
        return "bracelet";   
    }

    @GetMapping("/deleteform")
    public String showDeleteForm(Model model) {
        List<StoreItem> showItems = dataHandlerRead.ReadAll();
        model.addAttribute("itemsToDelete", showItems);
        return "delete_bracelet";
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
