package com.dellnaresh.controller;

import com.dellnaresh.db.Database;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by nareshm on 6/5/2016.
 */
@RestController
public class DbController {
    private Logger logger = LoggerFactory.getLogger(DbController.class);
    @Autowired
    private Database db;

    @RequestMapping(method = RequestMethod.POST, value = "/insertRecords")
    public void insert() {
        try {
            logger.info("Started db update");
            db.insert();
            logger.info("Completed db update");
        } catch (Exception e) {
            logger.error("Exception inserting records", e);
        }
    }
}
