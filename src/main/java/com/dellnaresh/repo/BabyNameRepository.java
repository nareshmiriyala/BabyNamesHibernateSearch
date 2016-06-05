package com.dellnaresh.repo;

import com.dellnaresh.model.BabyName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by nmiriyal on 4/06/2016.
 */
public interface BabyNameRepository extends JpaRepository<BabyName,Long> {
    public List<BabyName> findByNameAndYearAndGender(String name, int year,Character gender);
}
