package com.upliftfoundation.virtualstoreweb.Controllers;

import java.util.UUID;
import java.util.List;

import com.upliftfoundation.virtualstoreweb.DataHandlers.RepoDataHandlers.RepoCreateData;
import com.upliftfoundation.virtualstoreweb.DataHandlers.RepoDataHandlers.RepoDeleteData;
import com.upliftfoundation.virtualstoreweb.DataHandlers.RepoDataHandlers.RepoReadData;
import com.upliftfoundation.virtualstoreweb.DataHandlers.RepoDataHandlers.RepoUpdateData;
import com.upliftfoundation.virtualstoreweb.FundamentalObjects.StoreItem;
import com.upliftfoundation.virtualstoreweb.ProductObjects.Powerplant;
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
@RequestMapping("powerplant")
public class PowerplantController {
    
    UUID ID;
    @Autowired
    RepoCreateData<Powerplant> dataHandler;
    @Autowired
    RepoUpdateData<Powerplant> dataHandlerUpdate;
    @Autowired
    RepoDeleteData<Powerplant> dataHandlerDelete;
    @Autowired
    RepoReadData<Powerplant> dataHandlerRead;
    
    @GetMapping("/createform")
    public String showForm(Model model) {
        Powerplant powerPlant = new Powerplant();
        model.addAttribute("powerplant", powerPlant);
        return "create_powerplant";
    }

    @RequestMapping(value = "/createform", method = RequestMethod.POST)
    public String submit(@ModelAttribute("powerplant") Powerplant powerPlant, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return "error";
        }
        dataHandler.Create(powerPlant);
        //repo.save(shadowElf);
        model.addAttribute("powerplant", powerPlant);
        return "powerplant";
    }

    @RequestMapping(value = "/readform/{id}", method = RequestMethod.GET)
    public String read(@PathVariable("id") String Id, ModelMap model) {
        Powerplant powerplant = (Powerplant) dataHandlerRead.Read(UUID.fromString(Id));
        model.addAttribute("powerplant", powerplant);
        return "powerplant";
    }

    @RequestMapping(value = "/updateform/{id}", method = RequestMethod.GET)
    public String showUpdateForm(@PathVariable("id") String Id, Model model) {
        Powerplant powerplant = (Powerplant) dataHandlerRead.Read(UUID.fromString(Id));
        model.addAttribute("powerplant", powerplant);
        return "create_powerplant";
    }

    @RequestMapping(value="/updateform", method = RequestMethod.PUT)
    public String change(@ModelAttribute("powerplant") Powerplant powerplant, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return "error";
        }
        dataHandlerUpdate.Update(powerplant);
        return "powerplant";   
    }

    @GetMapping("/deleteform")
    public String showDeleteForm(Model model) {
        List<StoreItem> showItems = dataHandlerRead.ReadAll();
        model.addAttribute("itemsToDelete", showItems);
        return "delete_powerplant";
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