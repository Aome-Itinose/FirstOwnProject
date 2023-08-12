package org.Aome.model;

public class Person{
    private int id;
    private String nsf;
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