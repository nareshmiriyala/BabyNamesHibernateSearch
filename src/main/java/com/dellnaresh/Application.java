package com.dellnaresh;

import au.com.bytecode.opencsv.CSVReader;
import com.dellnaresh.model.BabyName;
import com.dellnaresh.repo.BabyNameRepository;
import com.dellnaresh.search.SearchIndex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.List;

@SpringBootApplication
public class Application implements CommandLineRunner {
    private Logger logger= LoggerFactory.getLogger(Application.class);
    @Autowired
    BabyNameRepository babyNameRepository;

    @Autowired
    private SearchIndex searchIndex;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        CSVReader reader = new CSVReader(readCSVFile());
        List<String[]> list = reader.readAll();
        list.parallelStream().forEach(entry -> {
            if (entry[2].equals("Year")) {
                return;
            }
            BabyName babyName = createBabyName(entry);
            logger.info("Inserting {}",babyName);
            babyNameRepository.save(babyName);

        });
        searchIndex.index();
    }

    private BabyName createBabyName(String[] entry) {
        return new BabyName(entry[1], Integer.parseInt(entry[2]), entry[3].charAt(0), Integer.parseInt(entry[4]));
    }

    private Reader readCSVFile() throws FileNotFoundException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("test.csv").getFile());
        return new FileReader(file);
    }
}
