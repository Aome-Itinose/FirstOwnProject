package org.Aome.model;

public class Book {
    private int id;
    private String name;
    private String author;
    private int year;
    private Integer owner_id;

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
}
