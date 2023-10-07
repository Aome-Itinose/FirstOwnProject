package org.Aome.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "Book")
public class Book {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Size(min = 1, max = 100, message = "Size of name should be between 1 and 100.")
    @Column(name = "name")
    private String name;


    @Max(value = 2023, message = "Year should be less than 2024.")
    @Column(name = "year_of_written")
    private int year;

    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private Person owner;

    @ManyToOne
    @JoinColumn(name="author_id", referencedColumnName = "id")
    private Author author;

    @Transient
    private int authorId;
    @Transient
    private int ownerId;


    public Book() {
    }

    public Book(String name, int year, Person owner, Author author) {
        this.name = name;
        this.year = year;
        this.owner = owner;
        this.author = author;
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }
}
