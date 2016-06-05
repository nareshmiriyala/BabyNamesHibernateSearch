package com.dellnaresh.controller;

import com.dellnaresh.model.BabyName;

import java.util.List;

/**
 * Created by nmiriyal on 4/06/2016.
 */
public interface NameSearch {
    List<BabyName> search(String text);
}
