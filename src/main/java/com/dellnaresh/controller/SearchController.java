package com.dellnaresh.controller;

import com.dellnaresh.model.BabyName;
import com.dellnaresh.repo.BabyNameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by nmiriyal on 4/06/2016.
 */
@RestController
public class SearchController {
    @Autowired
    private NameSearch nameSearch;
    @Autowired
    private BabyNameRepository babyNameRepository;
    /**
     * Show search results for the given query.
     *
     * @param query The search query.
     */
    @RequestMapping(method = RequestMethod.POST, value = "/search",consumes = {"application/json", "application/xml"})
    public List<BabyName> search(@RequestBody String query) {
        List<BabyName> searchResults = null;
        try {
            searchResults = nameSearch.search(query);
        }
        catch (Exception ex) {
            // here you should handle unexpected errors
            // ...
            // throw ex;
        }
        return searchResults;
    }
    @RequestMapping(value="/user", method= RequestMethod.POST)
    public String greetingSubmit(@ModelAttribute BabyName name, Model model) {
        model.addAttribute("name", name);
        babyNameRepository.save(name);
        return "result";
    }
    @RequestMapping(value="/name", method=RequestMethod.GET)
    public String greetingForm(Model model) {
        model.addAttribute("name", new BabyName());
        return "name";
    }
}
