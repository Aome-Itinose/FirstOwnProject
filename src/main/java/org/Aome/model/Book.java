package org.Aome.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Size;

public class Book {
    private int id;

    @Size(min = 1, max = 100, message = "Size of name should be between 1 and 100.")
    private String name;

    @Size(min = 2, max = 100, message = "Size of author name should be between 2 and 100.")
    private String author;

    @Max(value = 2023, message = "Year should be less than 2024.")
    private int year;
    private Integer owner_id;
    private Person owner;

    public Book(int id, String name, String author, int year, Integer owner_id) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.year = year;
        this.owner_id = owner_id;
    }

    public Book() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Integer getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(Integer owner_id) {
        this.owner_id = owner_id;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }
}
