package org.Aome.model;

import jakarta.validation.constraints.*;

public class Person{
    private int id;

    @Size(min = 2, max = 100, message = "Size should be between 2 and 100.")
    private String nsf;
    @Min(value = 1901, message = "Should be more than 1900.")
    @Max(value = 2023, message = "Should be less than 2024.")
    private int year;

    public Person(int id, String nsf, int year) {
        this.id = id;
        this.nsf = nsf;
        this.year = year;
    }

    public Person(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNsf() {
        return nsf;
    }

    public void setNsf(String nsf) {
        this.nsf = nsf;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}