package org.Aome.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "year_of_birth")
    private int yearOfBirth;

    @Column(name = "year_of_death")
    private int year_of_death;

    @OneToMany(mappedBy = "author")
    private List<Book> writtenBooks;

    public Author() {
    }

    public Author(String fullName, int yearOfBirth, int year_of_death, List<Book> writtenBooks) {
        this.fullName = fullName;
        this.yearOfBirth = yearOfBirth;
        this.year_of_death = year_of_death;
        this.writtenBooks = writtenBooks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public int getYear_of_death() {
        return year_of_death;
    }

    public void setYear_of_death(int year_of_death) {
        this.year_of_death = year_of_death;
    }

    public List<Book> getWrittenBooks() {
        return writtenBooks;
    }

    public void setWrittenBooks(List<Book> writtenBooks) {
        this.writtenBooks = writtenBooks;
    }

    @Override
    public String toString() {
        return fullName;
    }
}
