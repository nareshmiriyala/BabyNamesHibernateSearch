package com.dellnaresh.db;

import au.com.bytecode.opencsv.CSVReader;
import com.dellnaresh.model.BabyName;
import com.dellnaresh.repo.BabyNameRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.List;

import static java.util.Objects.isNull;

/**
 * Created by nareshm on 6/5/2016.
 */
@Component
public class DatabaseImpl implements Database {
    @Autowired
    BabyNameRepository babyNameRepository;
    private Logger logger = LoggerFactory.getLogger(DatabaseImpl.class);

    @Override
    public void insert() throws Exception {
        CSVReader reader = new CSVReader(readCSVFile());
        List<String[]> list = reader.readAll();
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "20");
        list.parallelStream().forEach(entry -> {
            if (entry[2].equals("Year")) {
                return;
            }
            BabyName babyName = createBabyName(entry);
            BabyName name = babyNameRepository.findOne(babyName.getId());
            if (isNull(name)) {
                logger.info("Inserting {}", babyName);
                babyNameRepository.save(babyName);
            }

        });
    }

    private BabyName createBabyName(String[] entry) {
        return new BabyName(Long.parseLong(entry[0]), entry[1], Integer.parseInt(entry[2]), entry[3].charAt(0), Integer.parseInt(entry[4]));
    }

    private Reader readCSVFile() throws FileNotFoundException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("test.csv").getFile());
        return new FileReader(file);
    }
}
