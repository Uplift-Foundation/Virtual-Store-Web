package com.upliftfoundation.virtualstoreweb.Controllers;

import java.util.UUID;
import java.util.List;

import com.upliftfoundation.virtualstoreweb.DataHandlers.RepoDataHandlers.RepoCreateData;
import com.upliftfoundation.virtualstoreweb.DataHandlers.RepoDataHandlers.RepoDeleteData;
import com.upliftfoundation.virtualstoreweb.DataHandlers.RepoDataHandlers.RepoReadData;
import com.upliftfoundation.virtualstoreweb.DataHandlers.RepoDataHandlers.RepoUpdateData;
import com.upliftfoundation.virtualstoreweb.FundamentalObjects.StoreItem;
import com.upliftfoundation.virtualstoreweb.ProductObjects.Computer;
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
@RequestMapping("computer")
public class ComputerController {

    UUID ID;
    @Autowired
    RepoCreateData<Computer> dataHandler;
    @Autowired
    RepoUpdateData<Computer> dataHandlerUpdate;
    @Autowired
    RepoDeleteData<Computer> dataHandlerDelete;
    @Autowired
    RepoReadData<Computer> dataHandlerRead;
    
    @GetMapping("/createform")
    public String showForm(Model model) {
        Computer computer = new Computer();
        model.addAttribute("computer", computer);
        return "create_computer";
    }

    @RequestMapping(value = "/createform", method = RequestMethod.POST)
    public String submit(@ModelAttribute("computer") Computer computer, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return "error";
        }
        dataHandler.Create(computer);
        //repo.save(shadowElf);
        model.addAttribute("computer", computer);
        return "computer";
    }

    @RequestMapping(value = "/readform/{id}", method = RequestMethod.GET)
    public String read(@PathVariable("id") String Id, ModelMap model) {
        Computer computer = (Computer) dataHandlerRead.Read(UUID.fromString(Id));
        model.addAttribute("computer", computer);
        return "computer";
    }
    
    @RequestMapping(value = "/updateform/{id}", method = RequestMethod.GET)
    public String showUpdateForm(@PathVariable("id") String Id, Model model) {
        Computer computer = (Computer) dataHandlerRead.Read(UUID.fromString(Id));
        model.addAttribute("computer", computer);
        return "create_computer";
    }

    @RequestMapping(value="/updateform", method = RequestMethod.PUT)
    public String change(@ModelAttribute("computer") Computer computer, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return "error";
        }
        dataHandlerUpdate.Update(computer);
        return "computer";   
    }

    @GetMapping("/deleteform")
    public String showDeleteForm(Model model) {
        List<StoreItem> showItems = dataHandlerRead.ReadAll();
        model.addAttribute("itemsToDelete", showItems);
        return "delete_computer";
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