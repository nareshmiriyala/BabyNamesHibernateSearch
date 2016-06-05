package com.dellnaresh.model;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by nmiriyal on 4/06/2016.
 */
@Entity
@Indexed
@Table(name = "babyname")
public class BabyName {
    @Id
    private long id;
    @Field
    @NotNull
    private String name;
    @Field
    @NotNull
    private int year;
    @Field
    @NotNull
    private Character gender;
    @Field
    @NotNull
    private int count;

    public BabyName() {
    }

    public BabyName(long id,String name, int year, Character gender, int count) {
        this.id=id;
        this.name = name;
        this.year = year;
        this.gender = gender;
        this.count = count;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Character getGender() {
        return gender;
    }

    public void setGender(Character gender) {
        this.gender = gender;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "BabyName{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", year=" + year +
                ", gender=" + gender +
                ", count=" + count +
                '}';
    }
}
