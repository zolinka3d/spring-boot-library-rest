package com.zosia.exam.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Author {

    private int id;

    private String name;

    private List<Book> books;

    public Author() {
    }

    public Author(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @OneToMany(fetch = FetchType.LAZY)
    @JsonBackReference
    public List<Book> getBooks() {
        return books;
    }


    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public void addBook(Book book) {
        if(books == null){
            books = new ArrayList<>();
        }
        books.add(book);
    }
    public void removeBook(Book book) {
        books.remove(book);
    }
    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
