package com.dellnaresh.controller;

import com.dellnaresh.model.BabyName;
import com.dellnaresh.search.SearchIndex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by nmiriyal on 4/06/2016.
 */
@RestController
public class SearchController {
    @Autowired
    private NameSearch nameSearch;

    @Autowired
    private SearchIndex searchIndex;

    /**
     * Show search results for the given query.
     *
     * @param query The search query.
     */
    @RequestMapping(method = RequestMethod.POST, value = "/search", consumes = {"application/json", "application/xml"})
    public List<BabyName> search(@RequestBody String query) {
        List<BabyName> searchResults = null;
        try {
            searchResults = nameSearch.search(query);
        } catch (Exception ex) {
            // here you should handle unexpected errors
            // ...
            // throw ex;
        }
        return searchResults;
    }

    @RequestMapping(value = "/indexRecords", method = RequestMethod.POST)
    public void indexData() {
        searchIndex.index();
    }

}
