package com.upliftfoundation.virtualstoreweb.Controllers;

import java.util.UUID;
import java.util.List;

import com.upliftfoundation.virtualstoreweb.DataHandlers.RepoDataHandlers.RepoCreateData;
import com.upliftfoundation.virtualstoreweb.DataHandlers.RepoDataHandlers.RepoDeleteData;
import com.upliftfoundation.virtualstoreweb.DataHandlers.RepoDataHandlers.RepoReadData;
import com.upliftfoundation.virtualstoreweb.DataHandlers.RepoDataHandlers.RepoUpdateData;
import com.upliftfoundation.virtualstoreweb.FundamentalObjects.StoreItem;
import com.upliftfoundation.virtualstoreweb.ProductObjects.Basketball;
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
//import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("basketball")
public class BasketballController {

    UUID ID;
    @Autowired
    RepoCreateData<Basketball> dataHandler;
    @Autowired
    RepoUpdateData<Basketball> dataHandlerUpdate;
    @Autowired
    RepoDeleteData<Basketball> dataHandlerDelete;
    @Autowired
    RepoReadData<Basketball> dataHandlerRead;

    @GetMapping("/createform")
    public String showForm(Model model) {
        Basketball basketball = new Basketball();
        model.addAttribute("basketball", basketball);
        return "create_basketball";
    }

    @RequestMapping(value = "/createform", method = RequestMethod.POST)
    public String submit(@ModelAttribute("basketball") Basketball basketball, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return "error";
        }
        dataHandler.Create(basketball);
        model.addAttribute("basketball", basketball);
        return "basketball";
    }

    @RequestMapping(value = "/readform/{id}", method = RequestMethod.GET)
    public String read(@PathVariable("id") String Id, ModelMap model) {
        Basketball basketball = (Basketball) dataHandlerRead.Read(UUID.fromString(Id));
        model.addAttribute("basketball", basketball);
        return "basketball";
    }
    
    @RequestMapping(value = "/updateform/{id}", method = RequestMethod.GET)
    public String showUpdateForm(@PathVariable("id") String Id, Model model) {
        Basketball basketball = (Basketball) dataHandlerRead.Read(UUID.fromString(Id));
        model.addAttribute("basketball", basketball);
        return "create_basketball";
    }

    @RequestMapping(value="/updateform", method = RequestMethod.PUT)
    public String change(@ModelAttribute("basketball") Basketball basketball, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return "error";
        }
        dataHandlerUpdate.Update(basketball);
        return "basketball";   
    }

    @GetMapping("/deleteform")
    public String showDeleteForm(Model model) {
        List<StoreItem> showItems = dataHandlerRead.ReadAll();
        model.addAttribute("itemsToDelete", showItems);
        return "delete_basketball";
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

