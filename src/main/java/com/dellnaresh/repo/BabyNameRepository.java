package com.dellnaresh.repo;

import com.dellnaresh.model.BabyName;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by nmiriyal on 4/06/2016.
 */
public interface BabyNameRepository extends JpaRepository<BabyName,Long> {
}
