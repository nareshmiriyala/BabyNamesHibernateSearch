package com.dellnaresh.controller;

import com.dellnaresh.model.BabyName;
import com.dellnaresh.repo.BabyNameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by nmiriyal on 4/06/2016.
 */
@Controller
public class SearchController {
    @Autowired
    private NameSearch nameSearch;
    @Autowired
    private BabyNameRepository babyNameRepository;
    /**
     * Index main page.
     */
    @RequestMapping("/")
    @ResponseBody
    public String index() {
        return "name";
    }
    /**
     * Show search results for the given query.
     *
     * @param q The search query.
     */
    @RequestMapping("/search")
    public String search(String q, Model model) {
        List<BabyName> searchResults = null;
        try {
            searchResults = nameSearch.search(q);
        }
        catch (Exception ex) {
            // here you should handle unexpected errors
            // ...
            // throw ex;
        }
        model.addAttribute("searchResults", searchResults);
        return "search";
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
