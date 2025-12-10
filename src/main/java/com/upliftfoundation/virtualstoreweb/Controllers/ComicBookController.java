package com.upliftfoundation.virtualstoreweb.Controllers;

import java.util.List;
import java.util.UUID;

import com.upliftfoundation.virtualstoreweb.DataHandlers.RepoDataHandlers.RepoCreateData;
import com.upliftfoundation.virtualstoreweb.DataHandlers.RepoDataHandlers.RepoDeleteData;
import com.upliftfoundation.virtualstoreweb.DataHandlers.RepoDataHandlers.RepoReadData;
import com.upliftfoundation.virtualstoreweb.DataHandlers.RepoDataHandlers.RepoUpdateData;
import com.upliftfoundation.virtualstoreweb.FundamentalObjects.StoreItem;
import com.upliftfoundation.virtualstoreweb.ProductObjects.BookObjects.ComicBook;
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
@RequestMapping("comicbook")
public class ComicBookController {

    UUID ID;
    @Autowired
    RepoCreateData<ComicBook> dataHandler;
    @Autowired
    RepoUpdateData<ComicBook> dataHandlerUpdate;
    @Autowired
    RepoDeleteData<ComicBook> dataHandlerDelete;
    @Autowired
    RepoReadData<ComicBook> dataHandlerRead;
    
    @GetMapping("/createform")
    public String showForm(Model model) {
        ComicBook comicBook = new ComicBook();
        model.addAttribute("comicbook", comicBook);
        return "create_comicbook";
    }

    @RequestMapping(value = "/createform", method = RequestMethod.POST)
    public String submit(@ModelAttribute("comicbook") ComicBook comicBook, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return "error";
        }
        dataHandler.Create(comicBook);
        //repo.save(shadowElf);
        model.addAttribute("comicbook", comicBook);
        return "comicbook";
    }

    @RequestMapping(value = "/readform/{id}", method = RequestMethod.GET)
    public String read(@PathVariable("id") String Id, ModelMap model) {
        ComicBook comicBook = (ComicBook) dataHandlerRead.Read(UUID.fromString(Id));
        model.addAttribute("comicbook", comicBook);
        return "comicbook";
    }
    
    @RequestMapping(value = "/updateform/{id}", method = RequestMethod.GET)
    public String showUpdateForm(@PathVariable("id") String Id, Model model) {
        ComicBook comicBook = (ComicBook) dataHandlerRead.Read(UUID.fromString(Id));
        model.addAttribute("comicbook", comicBook);
        return "create_comicbook";
    }

    @RequestMapping(value="/updateform", method = RequestMethod.PUT)
    public String change(ComicBook comicBook, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return "error";
        }
        dataHandlerUpdate.Update(comicBook);
        return "comicbook";   
    }

    @GetMapping("/deleteform")
    public String showDeleteForm(Model model) {
        List<StoreItem> showItems = dataHandlerRead.ReadAll();
        model.addAttribute("itemsToDelete", showItems);
        return "delete_comicbook";
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